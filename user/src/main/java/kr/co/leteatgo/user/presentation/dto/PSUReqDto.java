package kr.co.leteatgo.user.presentation.dto;

import common.aws.s3.AwsS3Folder;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PSUReqDto {

  private AwsS3Folder folder;
  private List<String> fileNames;
}
