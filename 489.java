/* Robot vacuum cleaner
Different from regular dfs to visit all, the robot move() function need to be called, backtrack needs to move() manually and backtracking path shold not be blocked by 
- IMPORTANT: Mark on the way in using set, but `backtrack directly without re-check against set` 
- Backtrack: turn 2 times to revert, move 1 step, and turn 2 times to revert back.
*/

  Class Solution {
     Int[] dx = {-1, 0, 1, 0};
     Int[] dy = {0, 1, 0, -1};
     Public void cleanRoom(Robot robot) {
         // use 'x@y' mark visited nodes, where x, y are integers tracking the coordinates
         Dfs(robot, new HashSet<>(), 0, 0, 0); // 0: up, 90: right, 180: down, 270: left
     }

     Public void dfs(Robot robot, Set<String> visited, int x, int y, int curDir) {
    	 String key = x + "@" + y;
    	 If (visited.contains(key)) return;
         Visited.add(key);
    	 Robot.clean();

    	 For (int i = 0; i < 4; i++) { // 4 directions
    		 If(robot.move()) { // can go directly. Find the (x, y) for the next cell based on current direction
    			 Dfs(robot, visited, x + dx[curDir], y + dy[curDir], curDir);
                 Backtrack(robot);
    		 } 

             // turn to next direction
    		 robot.turnRight();
    		 curDir += 1;
    		 curDir %= 4;
    	 }
     }

     // go back to the starting position
     Private void backtrack(Robot robot) {
         robot.turnLeft();
         robot.turnLeft();
         Robot.move();
         robot.turnRight();
         robot.turnRight();
     }
 } 
