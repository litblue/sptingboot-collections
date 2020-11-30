package cn.litblue.springbootxfyun.entity;

/**
 * @author litblue
 * @version 1.0.0
 * @time 2020/11/16  22:45
 */

public class ApiResultDto {
    private int ok;
    private int err_no;
    private String failed;
    private String data;

    public int getOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }

    public int getErr_no() {
        return err_no;
    }

    public void setErr_no(int err_no) {
        this.err_no = err_no;
    }

    public String getFailed() {
        return failed;
    }

    public void setFailed(String failed) {
        this.failed = failed;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
