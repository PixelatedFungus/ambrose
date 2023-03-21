package lusaris;

import java.util.List;

public class InterviewQ {

    /**
     * There are packages of data stored at different centers. The initial locations
     * of the centers are listed in locations.
     *
     * -- E.x: locations = [1, 7, 4, 3]
     *
     * The packages of data are then moved m times, where m is the length of the
     * moveFrom/moveTo array. A package of data is moved from location moveFrom[i] to
     * moveTo[i]. It is guaranteed that there will be no data package at location
     * moveTo[i]. Implement a function to move all the data packages in the locations
     * array according to moveFrom/moveTo arrays. Return the final locations in ascending order.
     *
     * -- E.x: locations = [1, 7, 3, 4]
     *         moveFrom = [7, 4, 2]
     *         moveTo = [2, 6, 5]
     *
     *         Process:
     *         - 7 is moved to 2
     *         -- (update) locations = [1, 2, 3, 4]
     *         - 4 is moved to 6
     *         -- (update) locations = [1, 2, 3, 6]
     *         - 2 is moved to 5
     *         -- (update) locations = [1, 5, 3, 6]
     *         - The locations must be sorted before returning
     *         -- (update) locations = [1, 3, 5, 6]
     *         result = [1, 3, 5, 6]
     * @param locations The locations of the data packages
     * @param moveFrom Where the packages are initially located
     * @param moveTo Where the packages are moved to
     * @return A list of ascending locations of packages after all the moves have been made.
     */
    public static List<Integer> problem1(List<Integer> locations, List<Integer> moveFrom, List<Integer> moveTo) {
        return null;
    }
}
