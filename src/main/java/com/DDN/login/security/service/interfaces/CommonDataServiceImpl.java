package com.DDN.login.security.service.interfaces;



import com.DDN.login.dto.ProductDto;
import com.DDN.login.dto.filter.*;
import com.DDN.login.model.categories.ApparelCategory;
import com.DDN.login.model.categories.GenderCategory;
import com.DDN.login.model.categories.PriceRangeCategory;
import com.DDN.login.model.categories.ProductBrandCategory;
import com.DDN.login.model.images.ApparelImages;
import com.DDN.login.model.images.BrandImages;
import com.DDN.login.model.images.CarouselImages;
import com.DDN.login.model.info.ProductInfo;
import com.DDN.login.payload.filter.FilterAttributesResponse;
import com.DDN.login.payload.filter.HomeTabsDataResponse;
import com.DDN.login.payload.filter.SearchSuggestionResponse;
import com.DDN.login.repository.dao.categories.*;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.javatuples.Pair;
import org.modelmapper.TypeToken;
import com.DDN.login.payload.filter.MainScreenResponse;
import com.DDN.login.repository.dao.images.ApparelImagesRepository;
import com.DDN.login.repository.dao.images.BrandImagesRepository;
import com.DDN.login.repository.dao.images.CarouselImagesRepository;
import com.DDN.login.repository.dao.info.ProductInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

@Service
public class CommonDataServiceImpl implements CommonDataService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private GenderCategoryRepository genderCategoryRepository;

    @Autowired
    private ApparelCategoryRepository apparelCategoryRepository;

    @Autowired
    private ProductBrandCategoryRepository productBrandCategoryRepository;

    @Autowired
    private BrandImagesRepository brandImagesRepository;

    @Autowired
    private ApparelImagesRepository apparelImagesRepository;

    @Autowired
    private CarouselImagesRepository carouselImagesRepository;

    @Autowired
    private SortByCategoryRepository sortByCategoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PriceRangeCategoryRepository priceRangeCategoryRepository;

    @Autowired
    private Cloudinary cloudinaryConfig;




    private HashMap<String, String> getConditionMapFromQuery(String queryParams) {
        queryParams = queryParams.concat("::");
        String[] separatedConditions = queryParams.split("::");

        if(separatedConditions.length > 0) {
            HashMap<String, String> conditionMap = new HashMap<>();
            for(String condition : separatedConditions) {
                String[] categories = condition.split("=");
                if(categories.length > 1){
                    conditionMap.put(categories[0], categories[1]);
                }
            }
            return conditionMap;
        }
        return null;
    }

    @Cacheable(key = "#apiName", value = "mainScreenResponse")
    public MainScreenResponse getHomeScreenData(String apiName){
        List<BrandImages> brandList = brandImagesRepository.getAllData();
        Type listType = new TypeToken<List<BrandImagesDTO>>() {
        }.getType();

        List<BrandImagesDTO> brandDTOList = modelMapper.map(brandList, listType);
        List<ApparelImages> apparelList = apparelImagesRepository.getAllData();
        listType = new TypeToken<List<ApparelIamgesDTO>>() {
        }.getType();
        List<ApparelIamgesDTO> apparelDTOList = modelMapper.map(apparelList, listType);

        List<CarouselImages> carouselList = carouselImagesRepository.getAllData();

        return new MainScreenResponse(brandDTOList, apparelDTOList, carouselList);
    }

    @Cacheable(key = "#queryParams", value = "filterAttributesResponse")
    public FilterAttributesResponse getFilterAttributesByProducts(String queryParams) {
        HashMap<String, String> conditionMap = getConditionMapFromQuery(queryParams);

        if(conditionMap != null && !conditionMap.isEmpty()) {
            FilterAttributesResponse filterAttributesResponse = productInfoRepository.getFilterAttributesByProducts(conditionMap);
            filterAttributesResponse.setSortby(sortByCategoryRepository.getAllData());
            return filterAttributesResponse;
        }
        return null;
    }

    @Cacheable(key = "#queryParams", value = "productInfoDTO")
    public ProductInfoDTO getProductsByCategories(String queryParams) {
        HashMap<String, String> conditionMap = getConditionMapFromQuery(queryParams);
        ProductInfoDTO productInfoDTO = null;
        if(conditionMap != null && !conditionMap.isEmpty()) {
            Pair<Long, List<ProductInfo>> result = productInfoRepository.getProductsByCategories(conditionMap);
            List<ProductInfo> resultList = result.getValue1();
            Long testValue = result.getValue0();
            if(result != null) {
                productInfoDTO = new ProductInfoDTO(result.getValue0(),result.getValue1());
            }
        }
        return productInfoDTO;
    }

    @Cacheable(key = "#queryParams", value = "hashMap")
    public HashMap<Integer,ProductInfo> getProductsById(String queryParams) {
        String[] productIds = queryParams.split(",");
        HashMap<Integer,ProductInfo> resultMap = null;

        if(productIds.length > 0) {
            List<ProductInfo> result = productInfoRepository.getProductsById(productIds);
            if(result != null) {
                resultMap = new HashMap<>();
                for(ProductInfo info : result) {
                    resultMap.put(info.getId(), info);
                }
            }
        }
        return resultMap;
    }

    @Cacheable(key = "#apiName", value = "homeTabsDataResponse")
    public HomeTabsDataResponse getBrandsAndApparelsByGender(String apiName) {
        return productInfoRepository.getBrandsAndApparelsByGender();
    }

    public SearchSuggestionResponse getSearchSuggestionList() {
        return new SearchSuggestionResponse(genderCategoryRepository.getAllData(),
                productBrandCategoryRepository.getAllData(), apparelCategoryRepository.getAllData(),
                productInfoRepository.getGenderAndApparelByIdAndName(),
                productInfoRepository.getGenderAndBrandByIdAndName(),
                productInfoRepository.getApparelAndBrandByIdAndName(),
                productInfoRepository.getGenderApparelBrandByIdAndName(),
                productInfoRepository.getProductByName());
    }

    public BrandsAndApparelsAndGenderDTO getCategoryList() {
        return new BrandsAndApparelsAndGenderDTO(productBrandCategoryRepository.getAllData(),
                apparelCategoryRepository.getAllData(),genderCategoryRepository.getAllData());
    }


    private Optional<PriceRangeCategory> findPriceRangeCategory(int price) {

        if(price <= 50) {
            return priceRangeCategoryRepository.findById(1);
        } else if(price <= 100) {
            return priceRangeCategoryRepository.findById(2);
        } else if(price <= 200) {
            return priceRangeCategoryRepository.findById(3);
        } else if(price <= 300) {
            return priceRangeCategoryRepository.findById(4);
        } else if(price <= 400) {
            return priceRangeCategoryRepository.findById(5);
        } else {
            return priceRangeCategoryRepository.findById(6);
        }
    }



    private File convertMultipartToFile(MultipartFile file) throws IOException {
        File covFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(covFile);
        fos.write(file.getBytes());
        fos.close();
        return covFile;
    }

    public void addProduct(ProductDto productDto, MultipartFile[] files) {
        ApparelCategory apparelCategory = apparelCategoryRepository.findByType(productDto.getApparelName());
        GenderCategory genderCategory = genderCategoryRepository.findByType(productDto.getGenderName());
        ProductBrandCategory brandCategory = productBrandCategoryRepository.findByType(productDto.getBrandName());
        String urlUpload = "";
        Optional<PriceRangeCategory> priceRangeCategory = findPriceRangeCategory(productDto.getPrice());

        try {
            List<MultipartFile> fileNames = new ArrayList<>();
            Arrays.asList(files).stream().forEach(file -> {
                fileNames.add(file);
            });

            File uploadFile = convertMultipartToFile(fileNames.get(0));
            Map uploadResult = cloudinaryConfig.uploader().upload(uploadFile, ObjectUtils.emptyMap());
            urlUpload = uploadResult.get("url").toString();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }

        if(apparelCategory != null && genderCategory != null && brandCategory !=null) {
            ProductInfo productInfo = new ProductInfo(1, productDto.getName(), new Date(),
                    brandCategory, genderCategory, apparelCategory, priceRangeCategory.get(),
                    productDto.getPrice(),productDto.getStock(),
                    2, 0,
                    true, null, urlUpload);
            productInfoRepository.save(productInfo);
        }

    }

    public List<ProductInfo> getAllProduct() {
        List<ProductInfo> productInfos = productInfoRepository.findAll();
        return productInfos;
    }

















}
