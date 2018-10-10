class Fish {
  int col;
  int row;
  int oldRow;
  int oldCol;
  int rdel = 7;
  int cdel = 7;
  int ht = 6 * ps;
  int wt = 14 * ps;
  int x = col + (wt / 2);
  int y = row + (ht / 2);
  color c;
  int type;
  boolean locked;
  boolean dontMove;
  float offset_x, offset_y;
  
  color[][] trail = new color[180][150];
  
  Fish(int col, int row, color c, int type){
      this.col = col;
      this.row = row;
      this.c = c;
      oldCol = col;
      oldRow = row;
      this.type = type;
    }
    
  void drawFish(){
    if (isClicked()) {
      locked = true;
    }
    if (locked && !dontMove) {
      
      col =  (int) (mouseX - offset_x);
      row =  (int) (mouseY - offset_y);
    }
    
    fill(c);
    //sprite for fish moving right
    if(col > oldCol && inBounds()) {
      for(int i = 0; i <= 0; i++) { rect(col + i * ps, row + 0 * ps, ps, ps);}
      for(int i = 6; i <= 11; i++) { rect(col + i * ps, row + 0 * ps, ps, ps);}
      for(int i = 0; i <= 1; i++) { rect(col + i * ps, row + 1 * ps, ps, ps);}
      for(int i = 5; i <= 12; i++) { rect(col + i * ps, row + 1 * ps, ps, ps);}
      for(int i = 0; i <= 2; i++) { rect(col + i * ps, row + 2 * ps, ps, ps);}
      for(int i = 4; i <= 13; i++) { rect(col + i * ps, row + 2 * ps, ps, ps);}
      for(int i = 0; i <= 13; i++) { rect(col + i * ps, row + 3 * ps, ps, ps);}
      for(int i = 4; i <= 13; i++) { rect(col + i * ps, row + 4 * ps, ps, ps);}
      for(int i = 0; i <= 2; i++) { rect(col + i * ps, row + 4 * ps, ps, ps);}
      for(int i = 0; i <= 1; i++) { rect(col + i * ps, row + 5 * ps, ps, ps);}
      for(int i = 5; i <= 12; i++) { rect(col + i * ps, row + 5 * ps, ps, ps);}
      for(int i = 0; i <= 0; i++) { rect(col + i * ps, row + 6 * ps, ps, ps);}
      for(int i = 6; i <= 11; i++) { rect(col + i * ps, row + 6 * ps, ps, ps);}
      fill(black);
      rect(col + 11 * ps, row + 2 * ps, ps, ps);
      //sprite for fish moving left
    } else if(inBounds()) {
      for(int i = 0; i <= 0; i++) { rect(col - i * ps, row + 0 * ps, ps, ps);}
      for(int i = 6; i <= 11; i++) { rect(col - i * ps, row + 0 * ps, ps, ps);}
      for(int i = 0; i <= 1; i++) { rect(col - i * ps, row + 1 * ps, ps, ps);}
      for(int i = 5; i <= 12; i++) { rect(col - i * ps, row + 1 * ps, ps, ps);}
      for(int i = 0; i <= 2; i++) { rect(col - i * ps, row + 2 * ps, ps, ps);}
      for(int i = 4; i <= 13; i++) { rect(col - i * ps, row + 2 * ps, ps, ps);}
      for(int i = 0; i <= 13; i++) { rect(col - i * ps, row + 3 * ps, ps, ps);}
      for(int i = 4; i <= 13; i++) { rect(col - i * ps, row + 4 * ps, ps, ps);}
      for(int i = 0; i <= 2; i++) { rect(col - i * ps, row + 4 * ps, ps, ps);}
      for(int i = 0; i <= 1; i++) { rect(col - i * ps, row + 5 * ps, ps, ps);}
      for(int i = 5; i <= 12; i++) { rect(col - i * ps, row + 5 * ps, ps, ps);}
      for(int i = 0; i <= 0; i++) { rect(col - i * ps, row + 6 * ps, ps, ps);}
      for(int i = 6; i <= 11; i++) { rect(col - i * ps, row + 6 * ps, ps, ps);}
      fill(black);
      rect(col - 11 * ps, row + 2 * ps, ps, ps);
    }
  }
  void drawTrail(){
    if((oldCol / ps < 180) && (oldRow / ps < 150) && (oldCol / ps > 0) 
      && (oldRow / ps > 0)) {
      pv[oldCol / ps][oldRow / ps] = c;
    }
  }

  boolean isOver() {
    float right_x = col + wt;
    float bottom_y = row + ht;
    return mouseX >= col && mouseX <= right_x &&
      mouseY >= row && mouseY <= bottom_y;
  }
  
  boolean isClicked() {
    return isOver() && mousePressed && !dontMove;
  }
  
  boolean inBounds() {
    return (col + wt >=0 && col + wt <= 720 && row + ht >= 0 && row + ht <= 600);
  }
}
