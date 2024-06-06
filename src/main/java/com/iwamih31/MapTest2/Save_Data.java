package com.iwamih31.MapTest2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor 
class Save_Data {
  public String data_Id;
  public Actor[] party;
  public String map_Number;
  public String x;
  public String y;
}
