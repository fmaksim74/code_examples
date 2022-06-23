package ru.uralprom.komplat.rest.payload;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class PayInfoKey
{
    public String ls;
    public String house;
    public String flat;
}
