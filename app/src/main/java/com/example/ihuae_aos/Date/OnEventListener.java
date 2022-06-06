package com.example.ihuae_aos.Date;

import com.example.ihuae_aos.Item.DayVO;

import java.util.Calendar;

public interface OnEventListener {
  void onClick(DayVO dayVO, int position);
}
