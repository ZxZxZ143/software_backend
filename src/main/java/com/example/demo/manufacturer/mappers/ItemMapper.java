package com.example.demo.manufacturer.mappers;

import com.example.demo.manufacturer.db.models.Item;
import com.example.demo.manufacturer.dto.ItemDetailDto;
import com.example.demo.manufacturer.dto.ItemDto;
import com.example.demo.manufacturer.dto.ItemResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public interface ItemMapper {
    @Mapping(source = "manufacturerId", target = "manufacturer.id")
    @Mapping(target = "id", ignore = true)
    Item toEntity(ItemDto itemDto);

    List<ItemResDto> toListDto(List<Item> items);

    @Mapping(source = "manufacturer", target = "manufacturer", qualifiedByName = "CountryToDto")
    ItemDetailDto toDetailDto(Item item);

    @Mapping(source = "manufacturerId", target = "manufacturer.id", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateItem(ItemDto itemDto, @MappingTarget Item item);
}
