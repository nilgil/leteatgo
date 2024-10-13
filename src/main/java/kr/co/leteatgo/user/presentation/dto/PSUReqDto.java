package kr.co.leteatgo.user.presentation.dto;

import java.util.List;

import kr.co.leteatgo.common.aws.s3.AwsS3Folder;
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
