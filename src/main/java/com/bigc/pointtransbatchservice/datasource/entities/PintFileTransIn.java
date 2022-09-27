package com.bigc.pointtransbatchservice.datasource.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "PINF_FILE_TRANF_IN")
public class PintFileTransIn {

    @Id
    @Column(name = "FILE_ID")
    private Long fileId;

    @Column(name = "MSG_ID")
    private Long msgId;

    @Column(name = "SEQ")
    private Long sqe;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "RECORD_COUNT")
    private String recordCount;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "FILE_TYPE")
    private String fileTyp;

    @Column(name = "DATA_STRING")
    private String dataString;

   /* @Column(name = "CREATE_BY")
    private String createBy;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "UPDATE_BY")
    private Date updateBy;

    @Column(name = "UPDATE_DATE")
    private Date updateDate;*/
}