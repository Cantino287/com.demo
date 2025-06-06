//package com.demo.POJO;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//
//import java.io.Serializable;
//
//@NamedQuery(name = "Bill.getAllBills",query = "select b from Bill b order by b.id desc")
//@NamedQuery(name = "Bill.getBillByUserName",query = "select b from Bill b where b.createdBy=:username order by b.id desc")
//@Data
//@Entity
//@DynamicUpdate
//@DynamicInsert
//@Table(name = "bill")
//public class Bill implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Integer id;
//
//    @Column(name = "uuid")
//    private String uuid;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "email")
//    private String email;
//
//    @Column(name = "contactnumber")
//    private String contactNumber;
//
//    @Column(name = "paymentmethod")
//    private String paymentMethod;
//
//    @Column(name = "total")
//    private Integer total;
//
//    @Column(name = "productdetails",columnDefinition = "json")
//    private String productDetail;
//
//    @Column(name = "createdby")
//    private String createdBy;
//
//
//}
package com.demo.POJO;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

//@NamedQuery(name = "Bill.getAllBills", query = "SELECT b FROM Bill b ORDER BY b.id DESC")
//@NamedQuery(name = "Bill.getBillByUserEmail", query = "SELECT b FROM Bill b WHERE b.email = :email ORDER BY b.id DESC")
@Data
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vvid")
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "paymentMethod")
    private String paymentMethod;

    @Column(name = "totalAmount")
    private int totalAmount;

    @Column(name = "productDetails")
    private String productDetails;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "orderTime")
    private LocalTime orderTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Column(name = "shopName")
    private String shopName;

    @Column(name = "deliveryFee")
    private int deliveryFee;

    @Column(name = "tax")
    private int tax;
}

