package club.lovety.webfileservice.common;

import club.lovety.webfileservice.fastdfs.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Created by 念梓  on 2017/2/17.
 * Email:sunmch@163.com
 * author: 念梓
 * des:上传fastdfs文件系统的工具类
 */
public final class UploadFdfsUtils {

    private static final Logger log = LogManager.getLogger(UploadFdfsUtils.class);

    public static UploadFdfsUtils uploadFdfsUtils = null;


    /**
     * 默认的fastdfs配置文件
     */
    private static String CONF_FILE_PATH = "fdfs_clinet.properties";

    private UploadFdfsUtils() {
        synchronized (UploadFdfsUtils.class) {
            if (null == uploadFdfsUtils) {
                uploadFdfsUtils = new UploadFdfsUtils();
            }
        }
    }

    public static UploadFdfsUtils getInstance() {
        return new UploadFdfsUtils();
    }

    public UploadFdfsUtils init() throws Exception {
        return init(CONF_FILE_PATH);
    }

    public UploadFdfsUtils init(String confFilePath) throws Exception {
        String filePath =UploadFdfsUtils.class.getClassLoader().getResource(confFilePath).getFile();
        ClientGlobal.init(filePath);
        log.debug("===================初始化fastdfs上传组件成功==================");
        return uploadFdfsUtils;
    }


    public String upload(File file) {


        return null;
    }

    public String upload(byte[] bytes) {
        log.debug("=======================开始上传文件到文件中心======================");
        TrackerClient tracker = new TrackerClient();
        String uploadResultUrl = null;
        try {
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            StorageClient client = new StorageClient(trackerServer, storageServer);
            String[] results = client.upload_appender_file(bytes,"txt",null);
            log.debug("====上传文件到文件中心成功,storename: {},newfilename:{}",results[0],results[1]);
            uploadResultUrl =results[1];
        } catch (Exception e) {
            log.error("=======上传文件失败，失败信息: ",e);
        }
        return uploadResultUrl;
    }


    public static void main(String[] args) {
        System.out.println(UploadFdfsUtils.class.getClassLoader().getResource(CONF_FILE_PATH).toString());
        String f = UploadFdfsUtils.class.getClassLoader().getResource(CONF_FILE_PATH).getFile();
        try {
            ClientGlobal.init(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
