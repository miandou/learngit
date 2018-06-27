package email.bean;

/**
 * Created by Administrator on 2018/4/21 0021.
 */
public class Log
{
    private int id;
    private String username;
    private String content;
    private String create_date;
    private int protocol_type;
    private int operate_type;
    private String ip;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public int getProtocol_type() {
        return protocol_type;
    }

    public void setProtocol_type(int protocol_type) {
        this.protocol_type = protocol_type;
    }

    public int getOperate_type() {
        return operate_type;
    }

    public void setOperate_type(int operate_type) {
        this.operate_type = operate_type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
