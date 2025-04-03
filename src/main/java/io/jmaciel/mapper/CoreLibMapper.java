package io.jmaciel.mapper;

import java.lang.reflect.Type;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;

public class CoreLibMapper {

  private static final ModelMapper modelMapper = createModelMapper();

  private static ModelMapper createModelMapper() {
    ModelMapper mapper = new ModelMapper();
    mapper.registerModule(new RecordModule());
    mapper.getConfiguration().setSkipNullEnabled(true);
    return mapper;
  }

  public static <D> D map(Object source, Type destination) {
    return modelMapper.map(source, destination);
  }

  public static void map(Object source, Object destination) {
    modelMapper.map(source, destination);
  }
}
