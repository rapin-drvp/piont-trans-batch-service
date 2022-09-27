package com.bigc.pointtransbatchservice.datasource.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "PINF_MESSAGE_IN")
public class PinMsgIn {
    @Id
    @Column(name = "MSG_ID")
    private Long msgId;

    @Column(name = "MSG_TYPE")
    private String msgType;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "CREATE_DATE")
    private Date createDate;
}
