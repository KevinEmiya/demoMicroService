package com.ky.reactive.model;

import lombok.Data;

import java.util.Date;

/**
 * Book
 *
 * @Author: kevin yang
 * @Description:
 * @Date: Created at 19-3-14
 * @Last Modified:
 **/

@Data
public class Book {

    long id;

    String title;

    String author;

    Date publishTime;

}
