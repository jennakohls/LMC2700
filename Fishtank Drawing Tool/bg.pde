void drawBG() {
  //draw a rectangle that is the color of the corresponding color in the pixel array
   for (int i = 0; i < 180; i++) {
    for (int j = 0; j < 150; j++) {
      fill(pv[i][j]);
      rect(i*ps, j*ps, ps, ps);
    }
  }
}

void initBG() {
  //fill pixel array with black then draw background objects and buttons
  for (int i = 0; i < 180; i++) {
    for (int j = 0; j < 150; j++) {
      pv[i][j] = black;
    }
  }
  drawDarkBlueCoral(32, 41);
  drawDarkBlueCoral(128,43);
  drawPinkCoral1(0, 43);
  drawPurpleCoral(43);
  drawPinkCoral1(149, 43);
  drawDarkBlueCoral(0, 43);
  for (Btn b: buttons){
    b.drawBtn();
  }
}
