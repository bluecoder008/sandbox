# We need to test whether a Sudoku solution is valid, testing
# the solution against the usual rules of Sudoku:
#
# https://en.wikipedia.org/wiki/Sudoku
#
# "The objective is to fill a 9×9 grid with digits so that each
# column, each row, and each of the nine 3×3 sub-grids that compose
# the grid (also called "boxes", "blocks", "regions", or "sub-squares")
# contains all of the digits from 1 to 9.

class Solution:
    board = {}
    DIGITS={1,2,3,4,5,6,7,8,9}

    def __init__(self, board):
        assert(len(board) == 9)
        assert(all(map(lambda l: l == 9, map(len, board))))
        self.board = board

    # Test if solution is valid.
    def is_valid(self):
        for row in self.board:
            the_set = set(row)
            if not (the_set == Solution.DIGITS):
                return False
        for col in range(9):
            the_set = set()
            for row in self.board:
                the_set.add(row[col])
            if not (the_set == Solution.DIGITS):
                return False
        for row in range(0,9,3):
            for col in range(0,9,3):
                the_set = set()
                # print("row={},col={}".format(row,col))
                for n in range(row,row+3):
                    for m in range(col,col+3):
                        the_set.add(self.board[n][m])
                if not (the_set == Solution.DIGITS):
                    return False
        return True

demo_board = [[5, 3, 4, 6, 7, 8, 9, 1, 2],
              [6, 7, 2, 1, 9, 5, 3, 4, 8],
              [1, 9, 8, 3, 4, 2, 5, 6, 7],
              [8, 5, 9, 7, 6, 1, 4, 2, 3],
              [4, 2, 6, 8, 5, 3, 7, 9, 1],
              [7, 1, 3, 9, 2, 4, 8, 5, 6],
              [9, 6, 1, 5, 3, 7, 2, 8, 4],
              [2, 8, 7, 4, 1, 9, 6, 3, 5],
              [3, 4, 5, 2, 8, 6, 1, 7, 9]]

solution = Solution(demo_board)
if solution.is_valid():
    print("Valid sudoku")
else:
    print("Invalid sudoku")

