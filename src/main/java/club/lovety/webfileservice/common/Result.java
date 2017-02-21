package club.lovety.webfileservice.common;

/**
 * Created by 念梓  on 2017/2/21.
 * Email:sunmch@163.com
 * author: 念梓
 * des:
 */
public class Result {

    private String msg = "成功";

    private String code = "00000";

    private Object obj;

    public Result() {

    }

    public Result(String msg, String code) {
        this.code = code;
        this.msg = msg;
    }

    public Result(String msg, String code, Object obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
