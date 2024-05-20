package com.example.techstore.domain.mapper;

import com.example.techstore.domain.dto.request.ProductRequestDto;
import com.example.techstore.domain.dto.response.ProductDto;
import com.example.techstore.domain.dto.response.ProductOptionSimpleDto;
import com.example.techstore.domain.dto.response.ProductSimpleDto;
import com.example.techstore.domain.entity.Category;
import com.example.techstore.domain.entity.Product;
import com.example.techstore.domain.entity.Product.ProductBuilder;
import com.example.techstore.domain.entity.ProductOption;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T01:31:31+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product mapProductRequestDtoToProduct(ProductRequestDto productRequestDto) {
        if ( productRequestDto == null ) {
            return null;
        }

        ProductBuilder product = Product.builder();

        product.name( productRequestDto.getName() );
        product.screenTechnology( productRequestDto.getScreenTechnology() );
        product.screenResolution( productRequestDto.getScreenResolution() );
        product.widescreen( productRequestDto.getWidescreen() );
        product.scanFrequency( productRequestDto.getScanFrequency() );
        product.rearCamera( productRequestDto.getRearCamera() );
        product.frontCamera( productRequestDto.getFrontCamera() );
        product.operationSystem( productRequestDto.getOperationSystem() );
        product.cpu( productRequestDto.getCpu() );
        product.multiplier( productRequestDto.getMultiplier() );
        product.numberStream( productRequestDto.getNumberStream() );
        product.cpuSpeed( productRequestDto.getCpuSpeed() );
        product.graphicChip( productRequestDto.getGraphicChip() );
        product.graphicCard( productRequestDto.getGraphicCard() );
        product.mobileNetwork( productRequestDto.getMobileNetwork() );
        product.sim( productRequestDto.getSim() );
        product.wifi( productRequestDto.getWifi() );
        product.gps( productRequestDto.getGps() );
        product.bluetooth( productRequestDto.getBluetooth() );
        product.headphoneJack( productRequestDto.getHeadphoneJack() );
        product.chargingPort( productRequestDto.getChargingPort() );
        product.connectionPort( productRequestDto.getConnectionPort() );
        product.webcam( productRequestDto.getWebcam() );
        product.batteryCapacity( productRequestDto.getBatteryCapacity() );
        product.batteryType( productRequestDto.getBatteryType() );
        product.chargingSupport( productRequestDto.getChargingSupport() );
        product.material( productRequestDto.getMaterial() );
        product.weight( productRequestDto.getWeight() );
        product.size( productRequestDto.getSize() );
        product.launchDate( productRequestDto.getLaunchDate() );
        product.supplier( productRequestDto.getSupplier() );

        return product.build();
    }

    @Override
    public ProductDto mapProductToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setCategoryId( productCategoryId( product ) );
        productDto.setCategoryName( productCategoryName( product ) );
        productDto.setCreatedDate( product.getCreatedDate() );
        productDto.setLastModifiedDate( product.getLastModifiedDate() );
        productDto.setCreatedBy( product.getCreatedBy() );
        productDto.setLastModifiedBy( product.getLastModifiedBy() );
        productDto.setId( product.getId() );
        productDto.setName( product.getName() );
        productDto.setAvatar( product.getAvatar() );
        productDto.setScreenTechnology( product.getScreenTechnology() );
        productDto.setScreenResolution( product.getScreenResolution() );
        productDto.setWidescreen( product.getWidescreen() );
        productDto.setScanFrequency( product.getScanFrequency() );
        productDto.setRearCamera( product.getRearCamera() );
        productDto.setFrontCamera( product.getFrontCamera() );
        productDto.setOperationSystem( product.getOperationSystem() );
        productDto.setCpu( product.getCpu() );
        productDto.setMultiplier( product.getMultiplier() );
        productDto.setNumberStream( product.getNumberStream() );
        productDto.setCpuSpeed( product.getCpuSpeed() );
        productDto.setGraphicChip( product.getGraphicChip() );
        productDto.setGraphicCard( product.getGraphicCard() );
        productDto.setMobileNetwork( product.getMobileNetwork() );
        productDto.setSim( product.getSim() );
        productDto.setWifi( product.getWifi() );
        productDto.setGps( product.getGps() );
        productDto.setBluetooth( product.getBluetooth() );
        productDto.setHeadphoneJack( product.getHeadphoneJack() );
        productDto.setChargingPort( product.getChargingPort() );
        productDto.setConnectionPort( product.getConnectionPort() );
        productDto.setWebcam( product.getWebcam() );
        productDto.setBatteryCapacity( product.getBatteryCapacity() );
        productDto.setBatteryType( product.getBatteryType() );
        productDto.setChargingSupport( product.getChargingSupport() );
        productDto.setMaterial( product.getMaterial() );
        productDto.setWeight( product.getWeight() );
        productDto.setSize( product.getSize() );
        productDto.setLaunchDate( product.getLaunchDate() );
        productDto.setSupplier( product.getSupplier() );

        return productDto;
    }

    @Override
    public List<ProductDto> mapProductsToProductDtos(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>( products.size() );
        for ( Product product : products ) {
            list.add( mapProductToProductDto( product ) );
        }

        return list;
    }

    @Override
    public ProductSimpleDto mapProductToProductSimpleDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductSimpleDto productSimpleDto = new ProductSimpleDto();

        productSimpleDto.setCategoryId( productCategoryId( product ) );
        productSimpleDto.setProductOptionSimpleDtos( productOptionListToProductOptionSimpleDtoList( product.getProductOptions() ) );
        productSimpleDto.setCreatedDate( product.getCreatedDate() );
        productSimpleDto.setLastModifiedDate( product.getLastModifiedDate() );
        productSimpleDto.setCreatedBy( product.getCreatedBy() );
        productSimpleDto.setLastModifiedBy( product.getLastModifiedBy() );
        productSimpleDto.setId( product.getId() );
        productSimpleDto.setName( product.getName() );
        productSimpleDto.setAvatar( product.getAvatar() );
        productSimpleDto.setScreenTechnology( product.getScreenTechnology() );
        productSimpleDto.setScreenResolution( product.getScreenResolution() );
        productSimpleDto.setWidescreen( product.getWidescreen() );

        return productSimpleDto;
    }

    @Override
    public List<ProductSimpleDto> mapProductsToProductSimpleDtos(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductSimpleDto> list = new ArrayList<ProductSimpleDto>( products.size() );
        for ( Product product : products ) {
            list.add( mapProductToProductSimpleDto( product ) );
        }

        return list;
    }

    @Override
    public void update(Product product, ProductRequestDto productRequestDto) {
        if ( productRequestDto == null ) {
            return;
        }

        if ( productRequestDto.getName() != null ) {
            product.setName( productRequestDto.getName() );
        }
        if ( productRequestDto.getScreenTechnology() != null ) {
            product.setScreenTechnology( productRequestDto.getScreenTechnology() );
        }
        if ( productRequestDto.getScreenResolution() != null ) {
            product.setScreenResolution( productRequestDto.getScreenResolution() );
        }
        if ( productRequestDto.getWidescreen() != null ) {
            product.setWidescreen( productRequestDto.getWidescreen() );
        }
        if ( productRequestDto.getScanFrequency() != null ) {
            product.setScanFrequency( productRequestDto.getScanFrequency() );
        }
        if ( productRequestDto.getRearCamera() != null ) {
            product.setRearCamera( productRequestDto.getRearCamera() );
        }
        if ( productRequestDto.getFrontCamera() != null ) {
            product.setFrontCamera( productRequestDto.getFrontCamera() );
        }
        if ( productRequestDto.getOperationSystem() != null ) {
            product.setOperationSystem( productRequestDto.getOperationSystem() );
        }
        if ( productRequestDto.getCpu() != null ) {
            product.setCpu( productRequestDto.getCpu() );
        }
        if ( productRequestDto.getMultiplier() != null ) {
            product.setMultiplier( productRequestDto.getMultiplier() );
        }
        if ( productRequestDto.getNumberStream() != null ) {
            product.setNumberStream( productRequestDto.getNumberStream() );
        }
        if ( productRequestDto.getCpuSpeed() != null ) {
            product.setCpuSpeed( productRequestDto.getCpuSpeed() );
        }
        if ( productRequestDto.getGraphicChip() != null ) {
            product.setGraphicChip( productRequestDto.getGraphicChip() );
        }
        if ( productRequestDto.getGraphicCard() != null ) {
            product.setGraphicCard( productRequestDto.getGraphicCard() );
        }
        if ( productRequestDto.getMobileNetwork() != null ) {
            product.setMobileNetwork( productRequestDto.getMobileNetwork() );
        }
        if ( productRequestDto.getSim() != null ) {
            product.setSim( productRequestDto.getSim() );
        }
        if ( productRequestDto.getWifi() != null ) {
            product.setWifi( productRequestDto.getWifi() );
        }
        if ( productRequestDto.getGps() != null ) {
            product.setGps( productRequestDto.getGps() );
        }
        if ( productRequestDto.getBluetooth() != null ) {
            product.setBluetooth( productRequestDto.getBluetooth() );
        }
        if ( productRequestDto.getHeadphoneJack() != null ) {
            product.setHeadphoneJack( productRequestDto.getHeadphoneJack() );
        }
        if ( productRequestDto.getChargingPort() != null ) {
            product.setChargingPort( productRequestDto.getChargingPort() );
        }
        if ( productRequestDto.getConnectionPort() != null ) {
            product.setConnectionPort( productRequestDto.getConnectionPort() );
        }
        if ( productRequestDto.getWebcam() != null ) {
            product.setWebcam( productRequestDto.getWebcam() );
        }
        if ( productRequestDto.getBatteryCapacity() != null ) {
            product.setBatteryCapacity( productRequestDto.getBatteryCapacity() );
        }
        if ( productRequestDto.getBatteryType() != null ) {
            product.setBatteryType( productRequestDto.getBatteryType() );
        }
        if ( productRequestDto.getChargingSupport() != null ) {
            product.setChargingSupport( productRequestDto.getChargingSupport() );
        }
        if ( productRequestDto.getMaterial() != null ) {
            product.setMaterial( productRequestDto.getMaterial() );
        }
        if ( productRequestDto.getWeight() != null ) {
            product.setWeight( productRequestDto.getWeight() );
        }
        if ( productRequestDto.getSize() != null ) {
            product.setSize( productRequestDto.getSize() );
        }
        if ( productRequestDto.getLaunchDate() != null ) {
            product.setLaunchDate( productRequestDto.getLaunchDate() );
        }
        if ( productRequestDto.getSupplier() != null ) {
            product.setSupplier( productRequestDto.getSupplier() );
        }
    }

    private String productCategoryId(Product product) {
        if ( product == null ) {
            return null;
        }
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        String id = category.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String productCategoryName(Product product) {
        if ( product == null ) {
            return null;
        }
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        String name = category.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected ProductOptionSimpleDto productOptionToProductOptionSimpleDto(ProductOption productOption) {
        if ( productOption == null ) {
            return null;
        }

        ProductOptionSimpleDto productOptionSimpleDto = new ProductOptionSimpleDto();

        productOptionSimpleDto.setId( productOption.getId() );
        productOptionSimpleDto.setRam( productOption.getRam() );
        productOptionSimpleDto.setStorageCapacity( productOption.getStorageCapacity() );
        productOptionSimpleDto.setColor( productOption.getColor() );
        productOptionSimpleDto.setImage( productOption.getImage() );
        productOptionSimpleDto.setPrice( productOption.getPrice() );
        productOptionSimpleDto.setQuantity( productOption.getQuantity() );
        productOptionSimpleDto.setStatus( productOption.getStatus() );

        return productOptionSimpleDto;
    }

    protected List<ProductOptionSimpleDto> productOptionListToProductOptionSimpleDtoList(List<ProductOption> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductOptionSimpleDto> list1 = new ArrayList<ProductOptionSimpleDto>( list.size() );
        for ( ProductOption productOption : list ) {
            list1.add( productOptionToProductOptionSimpleDto( productOption ) );
        }

        return list1;
    }
}
