package com.sprint.sb06deokhugamteam01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Profile("!test")
@Slf4j
public class LogUploaderScheduler {

    private final S3Client s3Client;

    @Value("${spring.cloud.aws.s3.log-bucket}")
    private String bucket;

    @Value("${log.path.archive}")
    private String localLogPath;

    public LogUploaderScheduler(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Scheduled(cron = "0 0 4 * * *")
    public void uploadDailyLogsToS3() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        String logFileName = "app-log-" + yesterday.format(DateTimeFormatter.ISO_DATE) + ".log";
        File logFile = new File(localLogPath + logFileName);

        if (logFile.exists() && logFile.isFile()) {
            try {
                // S3 키(경로) 설정: logs/YYYY/MM/DD/filename
                String s3Key = "logs/" + yesterday.getYear() + "/" + yesterday.getMonthValue() + "/" + yesterday.getDayOfMonth() + "/" + logFileName;

                // S3 업로드
                PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                        .bucket(bucket)
                        .key(s3Key)
                        // 필요하다면 ContentType 등을 설정할 수 있습니다.
                        .build();

                s3Client.putObject(putObjectRequest, RequestBody.fromFile(logFile));
                // 로그: 업로드 성공
                // logFile.delete(); // 업로드 후 삭제를 원할 경우 주석 해제

            } catch (S3Exception e) {
                log.error("S3 서비스 오류 발생: {}", e.getMessage(), e);
            } catch (SdkClientException e) {
                log.error("네트워크 오류 발생: {}", e.getMessage(), e);
            }
        }
    }
}