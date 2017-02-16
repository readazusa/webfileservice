package club.lovety.webfileservice.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @RequestMapping("upload")
    public Object upload(HttpServletRequest request) {
        return null;
    }

    @RequestMapping("del")
    public Object del(HttpServletRequest request) {
        return null;
    }

    public Object download() {
        return null;
    }


}
