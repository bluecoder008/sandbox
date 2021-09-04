"""

You're a dasher, and you want to try planning out your schedule. You can view a list of deliveries along with their associated start time, end time, and dollar amount for completing the order. Assuming dashers can only deliver one order at a time, determine the maximum amount of money you can make from the given deliveries.

The inputs are as follows:

int start_time: when you plan to start your schedule
int end_time: when you plan to end your schedule
int d_starts[n]: the start times of each delivery[i]
int d_ends[n]: the end times of each delivery[i]
int d_pays[n]: the pay for each delivery[i]
The output should be an integer representing the maximum amount of money you can make by forming a schedule with the given deliveries.

Assumptions:

end_time >= start_time
d_ends[i] >= d_starts[i]
d_pays[i] > 0
len(d_starts) == len(d_ends) == len(d_pays)

Example:

start_time = 0
end_time = 10
d_starts = [2, 3, 5, 7]
d_ends = [6, 5, 10, 11]
d_pays = [5, 2, 4, 1]

Expected: 6

"""


class delivery(object):
    def __init__(self, d_start, d_end, d_pay):
        self._d_start = d_start
        self._d_end = d_end
        self._d_pay = d_pay


deliveries = []
deliveries.append(delivery(10, 11, 12))


def check_overlap_with(delivery, delivery_list):
    overlapped = []
    for the_delivery in delivery_list:
        if the_delivery._d_end > delivery._d_end:
            overlapped.append(the_delivery)
    return overlapped


def sum(delivery_list):
    sum = 0
    for delivery in delivery_list:
        sum = sum + delivery._d_pay
    return sum


def get_max_pay(start_time, end_time, d_starts, d_ends, d_pays):
    deliveries = []
    for n in range(len(d_starts)):
        if d_starts[n] >= start_time and d_ends[n] <= end_time:
            deliveries.append(delivery(d_starts[n], d_ends[n], d_pays[n]))

    deliveries_to_include = []
    deliveries = sorted(deliveries, key=lambda x: x._d_start)
    for the_delivery in deliveries:
        overlapped = check_overlap_with(the_delivery, deliveries_to_include)
        if overlapped:
            new_deliveries_to_include = deliveries_to_include
            new_deliveries_to_include.append(the_delivery)
            for elem in overlapped:
                new_deliveries_to_include.remove(elem)
            if sum(new_deliveries_to_include) > sum(deliveries_to_include):
                deliveries_to_include = new_deliveries_to_include
        else:
            deliveries_to_include.append(the_delivery)
    return sum(deliveries_to_include)


def run_tests():
    test_data = [
        dict(
            start_time=0,
            end_time=10,
            d_starts=[2, 3, 5, 7],
            d_ends=[6, 5, 10, 11],
            d_pays=[5, 2, 4, 1],
            expect=6,
        ),
    ]

    for i, data in enumerate(test_data):
        expect = data.pop("expect")
        res = get_max_pay(**data)
        if res == expect:
            print(f"---- TEST {i+1} (PASSED) ----")
            pass
        else:
            print(f"---- TEST {i+1} (FAIL) ----")
            print(f"Expected: {expect}, got: {res}")
            print()


run_tests()
