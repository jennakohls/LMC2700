//very long blue coral sprite
void drawDarkBlueCoral(int y, int m){
  int ax = 23 + y;
  for (int k = 21 + y; k <= 23 + y; k++){ pv[k][86 + m] = gblue; }
  
  pv[20 + y][87 + m] = pv[23 + y][87 + m] = gblue;
  pv[21 + y][87 + m] = mblue;
  for (int k = 25 + y; k <= 27 + y; k++){ pv[k][87 + m] = gblue; }
  
  pv[18 + y][88 + m] = pv[19 + y][88 + m] = gblue;
  pv[20 + y][88 + m] = pv[21 + y][88 + m] = mblue;
  for (int k = 22 + y; k <= 26 + y; k++){ pv[k][88 + m] = gblue; }
  pv[28 + y][88 + m] = pv[29 + y][88 + m] = gblue;
  
  for (int k = 16 + y; k <= 18 + y; k++){ pv[k][89 + m] = gblue; }
  for (int k = 19 + y; k <= 21 + y; k++){ pv[k][89 + m] = mblue; }
  for (int k = 22 + y; k <= 24 + y; k++){ pv[k][89 + m] = gblue; }
  pv[27 + y][89 + m] = pv[29 + y][89 + m] = pv[30 + y][89 + m] = gblue;
  pv[25 + y][89 + m] = pv[26 + y][89 + m] = dblue;
  
  pv[16 + y][90 + m] = gblue;
  pv[18 + y][90 + m] = mblue;
  pv[19 + y][90 + m] = pv[20 + y][90 + m] = pv[27 + y][90 + m] = dblue;
  for (int k = 22 + y; k <= 24 + y; k++){ pv[k][90 + m] = gblue; }
  for (int k = 28 + y; k <= 30 + y; k++){ pv[k][90 + m] = gblue; }
  
  for (int k = 15 + y; k <= 18 + y; k++){ pv[k][91 + m] = gblue; }
  pv[19 + y][91 + m] = dblue;
  pv[20 + y][91 + m] = mblue;
  pv[21 + y][91 + m] = gblue;
  pv[26 + y][91 + m] = pv[27 + y][91 + m] = dblue;
  for (int k = 23 + y; k <= 25 + y; k++){ pv[k][91 + m] = gblue; }
  for (int k = 30 + y; k <= 31 + y; k++){ pv[k][91 + m] = gblue; }
  
  pv[16 + y][92 + m] = pv[23 + y][92 + m] = pv[25 + y][92 + m] = pv[27 + y][92 + m] = pv[28 + y][92 + m] = pv[31 + y][92 + m] = gblue;
  pv[24 + y][92 + m] = mblue;
  pv[26 + y][92 + m] = dblue;
  for (int k = 17 + y; k <= 20 + y; k++){ pv[k][92 + m] = mblue; }
  
  pv[11 + y][93 + m] = lblue;
  pv[12 + y][93 + m] = pv[14 + y][93 + m] = pv[17 + y][93 + m] = pv[28 + y][93 + m] = pv[30 + y][93 + m] = pv[31 + y][93 + m] = gblue;
  pv[23 + y][93 + m] = mblue;
  pv[13 + y][93 + m] = pv[15 + y][93 + m] = pv[24 + y][93 + m] = pv[27 + y][93 + m] = dblue;
  
  pv[27 + y][94 + m] = dblue;
  pv[11 + y][94 + m] = pv[13 + y][94 + m] = pv[16 + y][94 + m] = pv[29 + y][94 + m] = pv[31 + y][94 + m] = gblue;
  pv[20 + y][94 + m] = pv[23 + y][94 + m] = pv[28 + y][94 + m] = pv[32 + y][94 + m] = mblue;
  pv[22 + y][94 + m] = lblue;
  
  pv[12 + y][95 + m] = pv[13 + y][95 + m] = pv[20 + y][95 + m] = pv[25 + y][95 + m] = pv[27 + y][95 + m] = pv[28 + y][95 + m] = 
    pv[30 + y][95 + m] = pv[31 + y][95 + m] = gblue;
  pv[24 + y][95 + m] = pv[26 + y][95 + m] = dblue;
  pv[14 + y][95 + m] = pv[23 + y][95 + m] = pv[32 + y][95 + m] = pv[33 + y][95 + m] = pv[34 + y][95 + m] = mblue;
  pv[22 + y][95 + m] = lblue;
  
  pv[9 + y][96 + m] = pv[11 + y][96 + m] = pv[13 + y][96 + m] = pv[21 + y][96 + m] = pv[25 + y][96 + m] = pv[26 + y][96 + m] = 
    pv[27 + y][96 + m] = pv[31 + y][96 + m] = gblue;
  pv[14 + y][96 + m] = pv[23 + y][96 + m] = pv[24 + y][96 + m] = pv[33 + y][96 + m] = pv[34 + y][96 + m] = mblue;
  pv[22 + y][96 + m] = pv[29 + y][96 + m] = lblue;
  
  pv[9 + y][97 + m] = pv[10 + y][97 + m] = pv[18 + y][97 + m] = pv[19 + y][97 + m] =  pv[31 + y][97 + m] = pv[35 + y][97 + m] = 
    pv[36 + y][97 + m] = gblue;
  pv[15 + y][97 + m] = dblue;
  pv[14 + y][97 + m] = pv[16 + y][97 + m] = pv[24 + y][97 + m] = pv[29 + y][97 + m] = pv[30 + y][97 + m] = pv[33 + y][97 + m] = mblue;
  pv[22 + y][97 + m] = pv[23 + y][97 + m] = lblue;
  
  pv[19 + y][98 + m] = pv[20 + y][98 + m] = pv[21 + y][98 + m] = pv[24 + y][98 + m] = pv[25 + y][98 + m] = pv[28 + y][98 + m] = 
    pv[29 + y][98 + m] = pv[31 + y][98 + m] = pv[36 + y][98 + m] = pv[37 + y][98 + m] = pv[39 + y][98 + m] = pv[40 + y][98 + m] = gblue;
  pv[15 + y][98 + m] = pv[38 + y][98 + m] = dblue;
  pv[14 + y][98 + m] = pv[16 + y][98 + m] = pv[17 + y][98 + m] = pv[18 + y][98 + m] = pv[30 + y][98 + m] = pv[33 + y][98 + m] = 
    pv[34 + y][98 + m] = pv[35 + y][98 + m] = mblue;
  pv[22 + y][98 + m] = pv[23 + y][98 + m] = lblue;
  
  pv[19 + y][99 + m] = pv[20 + y][99 + m] = pv[25 + y][99 + m] = pv[28 + y][99 + m] = pv[30 + y][99 + m] = pv[32 + y][99 + m] = 
    pv[33 + y][99 + m] = pv[34 + y][99 + m] = pv[37 + y][99 + m] = pv[41 + y][99 + m] = gblue;
  pv[38 + y][99 + m] = pv[39 + y][99 + m] = pv[40 + y][99 + m] = dblue;
  pv[11 + y][99 + m] = pv[21 + y][99 + m] = pv[22 + y][99 + m] = pv[24 + y][99 + m] = pv[29 + y][99 + m] = mblue;
  for (int k = 14 + y; k <= 17 + y; k++){ pv[k][99 + m] = gblue; }
  pv[23 + y][99 + m] = pv[26 + y][99 + m] = pv[32 + y][99 + m] = lblue;
  
  pv[18 + y][100 + m] = pv[19 + y][100 + m] = pv[25 + y][100 + m] = pv[28 + y][100 + m] = pv[31 + y][100 + m] = pv[32 + y][100 + m] = 
    pv[38 + y][100 + m] = pv[39 + y][100 + m] = pv[41 + y][100 + m] = gblue;
  pv[11 + y][100 + m] = pv[14 + y][100 + m] = pv[15 + y][100 + m] = pv[16 + y][100 + m] = pv[24 + y][100 + m] = pv[35 + y][100 + m] = 
    pv[36 + y][100 + m] = pv[37 + y][100 + m] = pv[40 + y][100 + m] = dblue;
  pv[12 + y][100 + m] = pv[13 + y][100 + m] = pv[17 + y][100 + m] = pv[21 + y][100 + m] = mblue;
  pv[20 + y][100 + m] = pv[22 + y][100 + m] = pv[23 + y][100 + m] = pv[26 + y][100 + m] = lblue;
  
  pv[17 + y][101 + m] = pv[25 + y][101 + m] = pv[26 + y][101 + m] = pv[31 + y][101 + m] = pv[32 + y][101 + m] = pv[36 + y][101 + m] = 
    pv[37 + y][101 + m] = pv[42 + y][101 + m] = gblue;
  pv[10 + y][101 + m] = pv[12 + y][101 + m] = pv[14 + y][101 + m] = pv[19 + y][101 + m] = pv[21 + y][101 + m] = pv[27 + y][101 + m] = 
    pv[28 + y][101 + m] = pv[43 + y][101 + m] = dblue;  
  for (int k = 38 + y; k <= 41 + y; k++){ pv[k][101 + m] = dblue; }
  pv[13 + y][101 + m] = pv[16 + y][101 + m] = pv[20 + y][101 + m] = pv[23 + y][101 + m] = pv[24 + y][101 + m] = pv[24 + y][101 + m] = 
    pv[35 + y][101 + m] = mblue;  
  pv[18 + y][101 + m] = pv[22 + y][101 + m] = lblue;  
    
  pv[6 + y][102 + m] = pv[7 + y][102 + m] = pv[8 + y][102 + m] = pv[10 + y][102 + m] = pv[25 + y][102 + m] = pv[27 + y][102 + m] = 
    pv[30 + y][102 + m] = pv[31 + y][102 + m] = pv[32 + y][102 + m] = pv[39 + y][102 + m] = pv[40 + y][102 + m] = 
    pv[43 + y][102 + m] = gblue;
  pv[9 + y][102 + m] = pv[12 + y][102 + m] = pv[36 + y][102 + m] = pv[37 + y][102 + m] = pv[38 + y][102 + m] = pv[41 + y][102 + m] = 
    pv[42 + y][102 + m] = dblue;
  pv[11 + y][102 + m] = pv[19 + y][102 + m] = pv[20 + y][102 + m] = pv[22 + y][102 + m] = pv[24 + y][102 + m] = pv[33 + y][102 + m] = 
    pv[34 + y][102 + m] = pv[35 + y][102 + m] = mblue;
    pv[18 + y][102 + m] = pv[23 + y][102 + m] = pv[26 + y][102 + m] = pv[28 + y][102 + m] = lblue;
    
  pv[6 + y][103 + m] = pv[7 + y][103 + m] = pv[26 + y][103 + m] = pv[27 + y][103 + m] = pv[28 + y][103 + m] = pv[30 + y][103 + m] = 
    pv[35 + y][103 + m] = pv[39 + y][103 + m] = pv[42 + y][103 + m] = gblue;
  pv[9 + y][103 + m] = pv[10 + y][103 + m] = pv[11 + y][103 + m] = pv[14 + y][103 + m] = pv[25 + y][103 + m] = pv[38 + y][103 + m] = 
    pv[40 + y][103 + m] = pv[43 + y][103 + m] = dblue;
  pv[12 + y][103 + m] = pv[15 + y][103 + m] = pv[16 + y][103 + m] = pv[17 + y][103 + m] = pv[19 + y][103 + m] = pv[22 + y][103 + m] = 
    pv[24 + y][103 + m] = pv[33 + y][103 + m] = pv[34 + y][103 + m] = mblue;
  pv[13 + y][103 + m] = pv[18 + y][103 + m] = pv[23 + y][103 + m] = lblue;
    
  pv[6 + y][104 + m] = pv[20 + y][104 + m] =  pv[25 + y][104 + m] =  pv[27 + y][104 + m] =  pv[28 + y][104 + m] =  pv[30 + y][104 + m] 
    =  pv[31 + y][104 + m] =  pv[32 + y][104 + m] =  pv[35 + y][104 + m] = pv[43 + y][104 + m] =  gblue;  
  for (int k = 37 + y; k <= 40 + y; k++){ pv[k][104 + m] = gblue; }
  pv[21 + y][104 + m] =  pv[26 + y][104 + m] =  pv[36 + y][104 + m] =  pv[41 + y][104 + m] =  pv[42 + y][104 + m] =  dblue;  
  for (int k = 14 + y; k <= 18 + y; k++){ pv[k][104 + m] = dblue; }
  pv[13 + y][104 + m] =  pv[14 + y][104 + m] =  pv[22 + y][104 + m] =  pv[24 + y][104 + m] =  pv[33 + y][104 + m] =  
    pv[34 + y][104 + m] =  mblue;  
  pv[12 + y][104 + m] =  pv[19 + y][104 + m] =  pv[23 + y][104 + m] =  lblue;  
    
  pv[21 + y][105 + m] = pv[25 + y][105 + m] = pv[28 + y][105 + m] = pv[43 + y][105 + m] = pv[44 + y][105 + m] = gblue;
  for (int k = 27 + y; k <= 31 + y; k++){ pv[k][105 + m] = gblue; }
  for (int k = 33 + y; k <= 36 + y; k++){ pv[k][105 + m] = gblue; }
  pv[11 + y][105 + m] = pv[14 + y][105 + m] = pv[17 + y][105 + m] = pv[19 + y][105 + m] = pv[37 + y][105 + m] = dblue;
  pv[16 + y][105 + m] = pv[18 + y][105 + m] = pv[22 + y][105 + m] = pv[24 + y][105 + m] = pv[26 + y][105 + m] = pv[39 + y][105 + m] 
    = pv[42 + y][105 + m] = mblue;
  pv[15 + y][105 + m] = pv[20 + y][105 + m] = pv[23 + y][105 + m] = pv[40 + y][105 + m] = lblue;
  
  pv[1 + y][106 + m] = pv[2 + y][106 + m] = pv[13 + y][106 + m] = pv[27 + y][106 + m] = pv[34 + y][106 + m] = pv[35 + y][106 + m] = 
    pv[39 + y][106 + m] = pv[44 + y][106 + m] = pv[47 + y][106 + m] = pv[48 + y][106 + m] = pv[51 + y][106 + m] = gblue;
  pv[11 + y][106 + m] = pv[12 + y][106 + m] = pv[14 + y][106 + m] = pv[16 + y][106 + m] = pv[17 + y][106 + m] = pv[19 + y][106 + m] = pv[25 + y][106 + m] 
    = pv[36 + y][106 + m] = pv[43 + y][106 + m] = dblue;
  pv[15 + y][106 + m] = pv[18 + y][106 + m] = pv[24 + y][106 + m] = pv[38 + y][106 + m] = mblue;
  for (int k = 28 + y; k <= 33 + y; k++){ pv[k][106 + m] = mblue; }
  pv[26 + y][106 + m] = lblue;
  for (int k = 20 + y; k <= 23 + y; k++){ pv[k][106 + m] = lblue; }
  
if(m < 43) {  
  pv[26 + y][107 + m] = pv[27 + y][107 + m] = pv[28 + y][107 + m] = pv[29 + y][107 + m] = pv[31 + y][107 + m] = 
    pv[38 + y][107 + m] = pv[39 + y][107 + m] = pv[40 + y][107 + m] = pv[44 + y][107 + m] = pv[47 + y][107 + m] = 
    pv[48 + y][107 + m] = pv[49 + y][107 + m] = pv[50 + y][107 + m] = pv[51 + y][107 + m] = pv[52 + y][107 + m] = 
    pv[53 + y][107 + m] = gblue;
  pv[12 + y][107 + m] = pv[14 + y][107 + m] = pv[43 + y][107 + m] = dblue;
  pv[10 + y][107 + m] = pv[11 + y][107 + m] = pv[13 + y][107 + m] = pv[17 + y][107 + m] = pv[19 + y][107 + m] = 
    pv[32 + y][107 + m] = pv[33 + y][107 + m] = pv[34 + y][107 + m] = pv[35 + y][107 + m] = pv[36 + y][107 + m] = mblue;
  pv[21 + y][107 + m] = pv[22 + y][107 + m] = pv[23 + y][107 + m] = pv[37 + y][107 + m] = lblue;
  
  pv[0 + y][108 + m] = pv[1 + y][108 + m] = pv[12 + y][107 + m] = pv[14 + y][107 + m] = pv[17 + y][107 + m] = 
    pv[27 + y][108 + m] = pv[37 + y][108 + m] = pv[40 + y][108 + m] = pv[44 + y][108 + m] = pv[46 + y][108 + m] = 
    pv[52 + y][108 + m] = pv[54 + y][108 + m] = pv[43 + y][108 + m] = gblue;
  pv[2 + y][108 + m] = pv[3 + y][108 + m] = pv[11 + y][108 + m] = pv[18 + y][108 + m] = pv[42 + y][108 + m] = 
    pv[47 + y][108 + m] = pv[48 + y][108 + m] = pv[49 + y][108 + m] = pv[50 + y][108 + m] = pv[51 + y][108 + m] = 
    pv[53 + y][108 + m] = dblue;
  pv[10 + y][108 + m] = pv[11 + y][108 + m] = pv[13 + y][108 + m] = pv[17 + y][108 + m] = pv[19 + y][108 + m] = 
    pv[32 + y][108 + m] = pv[33 + y][108 + m] = pv[34 + y][108 + m] = pv[35 + y][108 + m] = pv[36 + y][108 + m] = mblue;
  pv[21 + y][108 + m] = pv[22 + y][108 + m] = pv[23 + y][108 + m] = pv[37 + y][108 + m] = lblue;
}  
  
}
