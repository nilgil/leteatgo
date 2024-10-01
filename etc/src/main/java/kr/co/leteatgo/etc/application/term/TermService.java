package kr.co.leteatgo.etc.application.term;

import common.aws.s3.AwsS3Folder;
import common.aws.s3.PreSignedUrl;
import common.aws.s3.PreSignedUrls;
import common.exception.ErrorCode;
import common.exception.LegException;
import in.wilsonl.minifyhtml.Configuration;
import in.wilsonl.minifyhtml.MinifyHtml;
import java.io.IOException;
import java.util.List;
import kr.co.leteatgo.etc.application.ApiSender;
import kr.co.leteatgo.etc.application.term.dto.CreateTermRequest;
import kr.co.leteatgo.etc.application.term.dto.TermResponse;
import kr.co.leteatgo.etc.application.term.dto.UpdateTermRequest;
import kr.co.leteatgo.etc.domain.term.Term;
import kr.co.leteatgo.etc.domain.term.TermRepository;
import kr.co.leteatgo.etc.domain.term.TermType;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class TermService {

  private final TermRepository termRepository;
  private final TermMapper termMapper;
  private final ApiSender apiSender;

  private final Configuration minifyConf = new Configuration.Builder().setMinifyCss(true).build();

  @Cacheable(value = "terms")
  @Transactional(readOnly = true)
  public List<TermResponse> getTerms(TermType type) {
    return termRepository.findAllByTypeAndParentIsNullOrderBySort(type).stream()
        .map(termMapper::termToResponse)
        .toList();
  }

  @CacheEvict(value = "terms", allEntries = true)
  @Transactional
  public PreSignedUrls createTerm(CreateTermRequest request) {

    Term parentTerm = getParentTerm(request.parentId(), request.type());

    String contentValue = request.content();
    Document doc = Jsoup.parse(contentValue);
    Elements imageElements = doc.select("img");

    PreSignedUrls preSignedUrls = apiSender.createPreSignedUrls(AwsS3Folder.TERM_IMAGES,
        imageElements.size());

    for (int i = 0; i < imageElements.size(); i++) {
      Element element = imageElements.get(i);
      PreSignedUrl preSignedUrl = preSignedUrls.get(i);
      String existSrc = element.attr("src");
      String replacedDoc = doc.toString().replace(existSrc, preSignedUrl.imageUrl());
      doc = Jsoup.parse(replacedDoc);
    }

    contentValue = MinifyHtml.minify(doc.toString(), minifyConf);
    contentValue = contentValue.replace("\"", "'");

    Term term = termMapper.create(request, contentValue, parentTerm);
    termRepository.save(term);

    return preSignedUrls.numbering("termImages");
  }

  @CacheEvict(value = "terms", allEntries = true)
  public void updateTerm(Long termId, UpdateTermRequest request) {
    Term term = getTermById(termId);
    Term parentTerm = getParentTerm(request.parentId(), term.getType());

    term = termMapper.update(term, parentTerm, request);
    termRepository.save(term);
  }

  @CacheEvict(value = "terms", allEntries = true)
  public void deleteTerm(Long termId) {
    Term term = getTermById(termId);
    termRepository.delete(term);
  }

  private Term getTermById(Long termId) {
    return termRepository.findById(termId)
        .orElseThrow(() -> new LegException(ErrorCode.NOT_FOUND_RESOURCE));
  }

  private Term getParentTerm(Long parentId, TermType childType) {
    if (parentId == null) {
      return null;
    }
    Term parentTerm = getTermById(parentId);
    if (parentTerm.getType() != childType) {
      throw new LegException(ErrorCode.BAD_REQUEST, "부모 약관의 타입과 일치하지 않습니다.");
    }
    return parentTerm;
  }
}
