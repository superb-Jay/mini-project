package com.fast.miniproject.search.controller;

import com.fast.miniproject.global.response.PageResponseDTO;
import com.fast.miniproject.global.response.ResponseDTO;
import com.fast.miniproject.search.service.SearchProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"검색 서비스"}, description = "검색 결과 반환 하는 서비스")
@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
@Slf4j
@CrossOrigin(origins = "*")
public class SearchProductController {

    private final SearchProductService searchProductService;
    public static final int PAGE_SIZE = 5;
    @ApiOperation(value = "검색 결과 반환", notes = "검색어에 따른 상품 리스트 페이징과 함께 반환")

    @GetMapping
    public ResponseDTO<PageResponseDTO> getProductByName(@RequestParam String name, @RequestParam(required = false, defaultValue = "1") String page) {
        PageRequest pageRequest = null;
        try {
            int intPage = Integer.parseInt(page);
            pageRequest = PageRequest.of(intPage - 1, PAGE_SIZE);
            //정상적인 범위 내의 페이지 번호면 해당 페이지로
        } catch (IllegalArgumentException e) {
            pageRequest = PageRequest.of(0, PAGE_SIZE);
            //음수나 오버플로 발생시키는 페이지 번호면 0번페이지로
            log.info(e.getMessage());
        }

        PageResponseDTO pageResponseDTO = new PageResponseDTO(searchProductService.searchProductsByName(name, pageRequest));
        return new ResponseDTO<PageResponseDTO>(pageResponseDTO);
    }
}