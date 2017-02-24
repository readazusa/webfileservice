package club.lovety.webfileservice.api;

import club.lovety.webfileservice.common.Result;
import club.lovety.webfileservice.common.UploadFdfsUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 念梓  on 2017/2/16.
 * Email:sunmch@163.com
 * author: 念梓
 * des: 文件上传接收接口
 */
@RestController
@RequestMapping("api")
public class ApiController {

    private static final Logger log = LogManager.getLogger(ApiController.class);

    private static final String UPLOAD_FILE_NAME = "filename";

    @Value("${fdfs.url}")
    private  String fdfsUrl;

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Object upload(HttpServletRequest request) {
        Result result = new Result();
        try {
            String uploadPath = fdfsUrl+uploadToFdfs(request);
            result.setData(uploadPath);
        } catch (Exception ex) {
            log.error("上传失败: ", ex);
            result.setCode("00001");
            result.setMsg(ex.getMessage());
        }
        return result;
    }

    @RequestMapping("del")
    public Object del(HttpServletRequest request) {
        return null;
    }

    public Object download() {
        return null;
    }


    private String uploadToFdfs(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile(UPLOAD_FILE_NAME);
//        String suffixName = getFileSuffixName(multipartFile.getOriginalFilename());
        String suffixName = getFileSuffixName(request);
        byte[] fileBytes = multipartFile.getBytes();
        UploadFdfsUtils uploadFdfsUtils = UploadFdfsUtils.getInstance().init();
        return uploadFdfsUtils.upload(fileBytes, suffixName);
    }

    private String getFileSuffixName(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }

    private String getFileSuffixName(HttpServletRequest request) {
        return request.getHeader("suffix");
    }
}
