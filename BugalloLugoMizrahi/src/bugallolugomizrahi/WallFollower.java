/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package bugallolugomizrahi;
//
///**
// *
// * @author juand
// */
//void wallFollower(int x, int y, int direction ){
//    error_check(vect, entrance_y, entrance_x, exit_y, exit_x);
//
//    int y = entrance_y;
//    int x = entrance_x;
//    uint32_t direction = north;
//    std::vector<uint32_t> directions(4);
//    std::vector<element> visited;
//    visited.push_back({y, x, 99u}); /* The first direction has to be invalid, so it never gets removed. */
//    vect[y][x] = solution;
//        }
//}
//
////    /* Loop until we aren't at the end. */
////    while(!((y == exit_y) && (x == exit_x)))
////    {
////
//        /* Left-hand rule and its priorities. */
//        if (left == rule)
//        {
//            if (north == direction)
//            {
//                directions = {west, north, east, south};
//            }
//            else if (south == direction)
//            {
//                directions = {east, south, west, north};
//            }
//            else if (west == direction)
//            {
//                directions = {south, west, north, east};
//            }
//            else if (east == direction)
//            {
//                directions = {north, east, south, west};
//            }
//            else
//            {
//                /* Do nothing. */
//            }
//        }
//        /* Right-hand rule and its priorities. */
//        else if (right == rule)
//        {
//            if (north == direction)
//            {
//                directions = {east, north, west, south};
//            }
//            else if (south == direction)
//            {
//                directions = {west, south, east, north};
//            }
//            else if (west == direction)
//            {
//                directions = {north, west, south, east};
//            }
//            else if (east == direction)
//            {
//                directions = {south, east, north, west};
//            }
//            else
//            {
//                /* Do nothing. */
//            }
//        }
//        else
//        {
//            /* Do nothing. */
//        }
//
//
//        /* Try to move in every direction. */
//        /* If it is possible to go there, then go (the directions are in priority order). */
//        /* If we haven't been there, then push it to the visited stack and mark as a solution. */
//        /* If we have been there, then pop it from the visited stack and remove the soliton mark. */
//        for (uint32_t i = 0u; i < directions.size(); i++)
//        {
//            if (north == directions[i])
//            {
//                if ((y > 0u) && (wall != vect[y-1u][x]))
//                {
//                    uint32_t last = visited.size()-1u;
//                    y--;
//                    direction = north;
//                    if (south == visited[last].direction)
//                    {
//                        vect[visited[last].y][visited[last].x] = hole;
//                        visited.pop_back();
//                    }
//                    else
//                    {
//                        visited.push_back({y, x, direction});
//                        vect[y][x] = solution;
//                    }
//                    break;
//                }
//            }
//            else if (south == directions[i])
//            {
//                if (((y+1u) < vect.size()) && (wall != vect[y+1u][x]))
//                {
//                    uint32_t last = visited.size()-1u;
//                    y++;
//                    direction = south;
//                    if (north == visited[last].direction)
//                    {
//                        vect[visited[last].y][visited[last].x] = hole;
//                        visited.pop_back();
//                    }
//                    else
//                    {
//                        visited.push_back({y, x, direction});
//                        vect[y][x] = solution;
//                    }
//                    break;
//                }
//            }
//            else if (west == directions[i])
//            {
//                if ((x > 0u) && (wall != vect[y][x-1u]))
//                {
//                    uint32_t last = visited.size()-1u;
//                    x--;
//                    direction = west;
//                    if (east == visited[last].direction)
//                    {
//                        vect[visited[last].y][visited[last].x] = hole;
//                        visited.pop_back();
//                    }
//                    else
//                    {
//                        visited.push_back({y, x, direction});
//                        vect[y][x] = solution;
//                    }
//                    break;
//                }
//            }
//            else if (east == directions[i])
//            {
//                if (((x+1u) < vect[0u].size()) && (wall != vect[y][x+1u]))
//                {
//                    uint32_t last = visited.size()-1u;
//                    x++;
//                    direction = east;
//                    if (west == visited[last].direction)
//                    {
//                        vect[visited[last].y][visited[last].x] = hole;
//                        visited.pop_back();
//                    }
//                    else
//                    {
//                        visited.push_back({y, x, direction});
//                        vect[y][x] = solution;
//                    }
//                    break;
//                }
//            }
//            else
//            {
//                /* Do nothing. */
//            }
//        }
//    }
//}
//
///**
// * @brief   Error handler for the solver algorithms. It shall be insterted into every member function.
// * @param   &vect       - The vector-vector of the maze we want to solve. It overwrites the input one.
// * @param   entrance_y  - Y coordinate of the entrance.
// * @param   entrance_x  - X coordinate of the entrance.
// * @param   exit_y      - Y coordinate of the exit.
// * @param   exit_x      - X coordinate of the exit.
// * @return  void
// */
//void maze::solver::error_check(std::vector<std::vector<uint32_t>> vect, uint32_t entrance_y, uint32_t entrance_x, uint32_t exit_y, uint32_t exit_x)
//{
//    if ((vect.size() <= entrance_y) || (vect[0u].size() <= entrance_x) || (vect.size() <= exit_y) || (vect[0u].size() <= exit_x))
//    {
//        throw std::invalid_argument("Out of boundary!");
//    }
//
//    if ((hole != vect[entrance_y][entrance_x]) || (hole != vect[entrance_y][entrance_x]))
//    {
//        throw std::invalid_argument("The entrance and exit must be holes (0).");
//    }