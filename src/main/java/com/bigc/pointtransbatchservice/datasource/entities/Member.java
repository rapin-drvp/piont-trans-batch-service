package com.bigc.pointtransbatchservice.datasource.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "MEMBER")
public class Member {
    @Id
    @Column(name="ROW_ID")
    String rowId;

    @Column(name="MEMBER_FIRST_NAME")
    String memberFirstName;

    @Column(name="MEMBER_LAST_NAME")
    String memberLastName;

    @Column(name="SEX")
    String sex;

    @Column(name="AGE")
    String age;
}