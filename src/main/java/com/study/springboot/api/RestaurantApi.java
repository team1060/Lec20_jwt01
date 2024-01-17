package com.study.springboot.api;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.springboot.api.request.CreateAndEditRestaurantRequest;
import com.study.springboot.api.response.RestaurantDetailView;
import com.study.springboot.api.response.RestaurantView;
import com.study.springboot.service.RestaurantService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class RestaurantApi {

	private final RestaurantService restaurantService;

	@SecurityRequirement(name = "restaurants")
	@GetMapping("/restaurants")
	public List<RestaurantView> getRestaurants() {
		return restaurantService.getAllRestaurants();
	}

	@Operation(summary = "매장생성", description = "Creates a new restaurant based on the provided information")
	@PostMapping("/restaurant")
	public void createRestaurant(@RequestBody CreateAndEditRestaurantRequest request) {
		restaurantService.createRestaurant(request);
	}

	@Operation(summary = "레스토랑수정", description = "레스토랑정보수정")
	@PutMapping("/restaurant/{restaurantId}")
	public void editRestaurant(
			@Parameter(name = "restaurantId", description = "레스토랑 ID", in = ParameterIn.PATH, schema = @Schema(type = "integer")) @PathVariable Long restaurantId,
			@RequestBody CreateAndEditRestaurantRequest request) {
		restaurantService.editRestaurant(restaurantId, request);
	}

	@Operation(summary = "레스토랑삭제", description = "레스토랑정보삭제")
	@DeleteMapping("/restaurant/{restaurantId}")
	public void deleteRestaurant(
			@Parameter(name = "restaurantId", description = "레스토랑 ID", in = ParameterIn.PATH, schema = @Schema(type = "integer")) @PathVariable Long restaurantId) {
		restaurantService.deleteRestaurant(restaurantId);
	}

	@Operation(summary = "레스토랑/메뉴 정보보기", description = "레스토랑/메뉴 정보보기")
	@GetMapping("/restaurant/{restaurantId}")
	public RestaurantDetailView getRestaurant(
			@Parameter(name = "restaurantId", description = "레스토랑 ID", in = ParameterIn.PATH, schema = @Schema(type = "integer")) @PathVariable Long restaurantId
			) {
		
		return restaurantService.getRestaurantDetail(restaurantId);
	}

}
