package cva.gson.request;

/**
 * Created by ko on 2015-02-12.
 */
public class Request {
    UserInfo user_info;

    public Request(UserInfo user_info)
    {
        this.user_info = user_info;
    }

    public UserInfo getUser_info() {
        return user_info;
    }
}
