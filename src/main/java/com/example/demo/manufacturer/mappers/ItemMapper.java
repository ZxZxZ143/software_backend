package com.example.demo.manufacturer.mappers;

import com.example.demo.manufacturer.db.models.Item;
import com.example.demo.manufacturer.dto.ItemDetailDto;
import com.example.demo.manufacturer.dto.ItemDto;
import com.example.demo.manufacturer.dto.ItemResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public interface ItemMapper {
    Item toEntity(ItemDto itemDto);

    List<ItemResDto> toListDto(List<Item> items);

    @Mapping(source = "manufacturer", target = "manufacturer", qualifiedByName = "CountryToDto")
    ItemDetailDto toDetailDto(Item item);
}
