class Bubble{
  int col;
  int row;
  int cdel;
  int rdel;
  
  Bubble(int col, int row){
    this.col = col;
    this.row = row;
  }
  
  void drawBubble(){
    fill(color(36,76,116, 100));
    rect(col, row, ps, ps);
  }
}
