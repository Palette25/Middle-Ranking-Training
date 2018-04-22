## Part3 Design Report

1. ### Clarity and answer the details of Part3

   * In my design, if the location two cells in front contains a flower, then the jumper will jump to the location and remove the flower, but if it's a rock, then the jumper will turn and find another available direction.And I design the jumper to perform two steps jumping, and never will it jump just one step.

   * If the location two cells in front is out of grid, then my jumper will turn to another direction, and find another available direction to perform its two-step jump.

   * The jumper will turn for several times to change its direction until not facing and can move, then keep jumping.

   * My designed jumper will also turn to another direction if there is another type actor two cells in front of it.

   * If the jumper encounters another jumper in its path, and if they are going to jump into the same location, then we will have the faster one jump into the location two cells front, the slower one turn its direction, and usually, the jumper's location that has less row num and col num, that is closer to the left_up part of grid, is often faster cause the gridworld use loop to act all actors.

   * I think we need to make a test that if the jumper is facing the rock and the location two cell in front is always rock,then we test what will the jumper does. In my design, I simply turn the jumper to another direction instead of jumping three steps.

     ​