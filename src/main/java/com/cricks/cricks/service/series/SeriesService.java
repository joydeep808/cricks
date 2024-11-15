package com.cricks.cricks.service.series;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cricks.cricks.entity.Series;
import com.cricks.cricks.exception.thrown_exception.series.SeriesNotFound;
import com.cricks.cricks.mapper.series.SeriesWiseMatchsDetailsMapper;
import com.cricks.cricks.repository.SeriesRepo;
import com.cricks.cricks.util.Response;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SeriesService {
  private final SeriesRepo seriesRepo;
  public ResponseEntity<Response<String>> createSeries(Series series){
    seriesRepo.save(series);
    return new Response<String>().sendSuccessResponse("Series created successfully" , 200).sendResponseEntity();
  }
  public ResponseEntity<Response<Series>> getSeries(Integer id){
    Response<Series> response = new Response<Series>();;
    Series series = seriesRepo.findById(id).orElseGet(null);
    if (series == null) {
      return response.sendErrorResponse("Series not found " , 404).sendResponseEntity();
    }
    return response.sendSuccessResponse("Series found" , 200 , series).sendResponseEntity();
  }


  public ResponseEntity<Response<List<Series>>> getAllSeriesByDateRange(LocalDate starDate , LocalDate endDate) throws Exception{

    List<Series> series = seriesRepo.getSeriesByDateRange(starDate , endDate).orElseGet(null);
    if (series == null) {
      throw new SeriesNotFound("Series not found", 404);
    }
    return new Response<List<Series>>().sendSuccessResponse("Series found" , 200 , series).sendResponseEntity();
  }

  public ResponseEntity<Response<List<SeriesWiseMatchsDetailsMapper>>> getTheMatchesWithTheSeries(Integer series_id){
    Response<List<SeriesWiseMatchsDetailsMapper>> response = new Response<List<SeriesWiseMatchsDetailsMapper>>();
    List<SeriesWiseMatchsDetailsMapper> series = seriesRepo.findTheMatchesWithTheSeries(series_id).orElseGet(null);
    if (series == null) {
      return response.sendErrorResponse("Series not found " , 404).sendResponseEntity();
    }
    return response.sendSuccessResponse("Series found" , 200 , series).sendResponseEntity();
  }
  
}
