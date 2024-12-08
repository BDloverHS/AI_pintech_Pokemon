package org.koreait.dl.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Profile("dl")
@Service
public class TrainService {

    // dl이라는 프로파일이 활성화 되었을 때만 불러오기 위함

    @Value("${python.run.path}")
    private String runPath;

    @Value("${python.script.path}")
    private String scriptPath;

    @Value("${python.data.url}")
    private String dataUrl;

    @Scheduled(cron="0 0 1 * * *") // 새벽 1시마다 훈련
    public void process() {
        try {
            ProcessBuilder builder = new ProcessBuilder(runPath, scriptPath + "train.py", dataUrl);
            Process process = builder.start();
            int exitCode = process.waitFor();
        } catch (Exception e) {}
    }
}
