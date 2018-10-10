class Food {
  color c; 
  int col;
  int row;
  boolean active;
  int x = col + (ps / 2);
  int y = row + (ps / 2);
  int type;
  
  Food(int col, int row, color c, boolean a){
    this.col = col;
    this.row = row;
    this.c = c;
    active = a;
    type = 1;
  }
  
  Food(int col, int row, color c, boolean a, int t){
    this.col = col;
    this.row = row;
    this.c = c;
    active = a;
    type = t;
  }
  
  void drawFood(){
    if(active) {
      fill(c);
      rect(col, row, ps, ps, c);
    }
  }
    
}
