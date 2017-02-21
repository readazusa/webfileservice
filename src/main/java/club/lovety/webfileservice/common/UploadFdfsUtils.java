package club.lovety.webfileservice.common;

import club.lovety.webfileservice.fastdfs.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Created by 念梓  on 2017/2/17.
 * Email:sunmch@163.com
 * author: 念梓
 * des:上传fastdfs文件系统的工具类
 */
public class UploadFdfsUtils {

    private static final Logger log = LogManager.getLogger(UploadFdfsUtils.class);

    public static UploadFdfsUtils uploadFdfsUtils = null;


    /**
     * 默认的fastdfs配置文件
     */
    private static String CONF_FILE_PATH = "fdfs_clinet.properties";

    private UploadFdfsUtils() {
    }

    public static UploadFdfsUtils getInstance() {
        synchronized (UploadFdfsUtils.class) {
            if (null == uploadFdfsUtils) {
                uploadFdfsUtils = new UploadFdfsUtils();
            }
        }
        return uploadFdfsUtils;
    }

    public UploadFdfsUtils init() throws Exception {
        return init(CONF_FILE_PATH);
    }

    public UploadFdfsUtils init(String confFilePath) throws Exception {
        ClientGlobal.init(confFilePath);
        log.debug("===================初始化fastdfs上传组件成功==================");
        return uploadFdfsUtils;
    }

    public String upload(File file) {
        return null;
    }

    public String upload(byte[] bytes) throws Exception {
        return upload(bytes, "txt");
    }

    public String upload(byte[] bytes, String suffix) throws Exception {
        log.debug("=======================开始上传文件到文件中心======================");
        TrackerClient tracker = new TrackerClient();
        String uploadResultUrl = null;
        TrackerServer trackerServer = tracker.getConnection();
        StorageServer storageServer = null;
        StorageClient client = new StorageClient(trackerServer, storageServer);
        String[] results = client.upload_appender_file(bytes, suffix, null);
        log.debug("====上传文件到文件中心成功,storename: {},newfilename:  {}", results[0], results[1]);
        uploadResultUrl = results[1];
        return uploadResultUrl;
    }


}
