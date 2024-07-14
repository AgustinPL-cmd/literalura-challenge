package com.agustin.literalura.service;

public interface IDataConvert {
    <T> T getData(String json, Class<T> kind);
}
