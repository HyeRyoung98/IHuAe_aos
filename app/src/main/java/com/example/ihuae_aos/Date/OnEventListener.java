package com.example.ihuae_aos.Date;

import java.util.Calendar;

public interface OnEventListener {
  void onClick(Calendar day, int position, int month, int status, String content);
}
