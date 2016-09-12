package com.roxoft.hierarchy.mybatis.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Select;

import com.roxoft.hierarchy.models.address.Address;

public interface AddressMapper {

	@Select("SELECT postcode, city, street, house FROM addresses")
	ArrayList<Address> getAllData();
	
	@Select("SELECT postcode, city, street, house FROM addresses WHERE address_id = #{address_id}")
	Address getAddressById(int addressId);
	
}
