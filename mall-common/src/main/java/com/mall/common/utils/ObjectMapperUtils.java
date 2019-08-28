package com.mall.common.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectMapperUtils{

    private static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }


    /**
    *
    * 对象entity和dto互相转换
    */
    public static <T, D> D map(final T source, Class<D> outClass){
        if (source == null){
            return null;
        }
        return modelMapper.map(source, outClass);
    }


    /**
    * 列表entity和dto互相转换
    */
    public static <T, D> List<D> mapAll(final Collection<T> sourceList, Class<D> outClass){
        if (sourceList == null){
            return null;
        }
        return sourceList.stream()
                .map(source -> map(source, outClass))
                .collect(Collectors.toList());
    }

}
