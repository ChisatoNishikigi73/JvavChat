package src.pakage.info;

public interface PackageOpcodes {

    // Empty
    int NONE = 0;

    // Opcodes
    int HandShake0 = 200; // 客户端发送连接请求
    int HandShake_Request = 201; // 服务端收到
    int HandShake_Info = 202; // 客户端发送用户数据包
    int HandShake_3_error = 2020; //用户数据包错误
    int HostIp = 300; // 聊天室地址数据包
}
