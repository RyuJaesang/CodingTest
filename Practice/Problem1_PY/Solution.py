test_num = int(input())

def solution():
    input_len = int(input())
    input_arr = []

    for i in range(input_len):
        input_arr.append(int(input()))

    input_arr.sort()
    input_arr = swap_arr(input_arr)

    count = count_result(input_arr)

    print(count)

def count_result(input):
    count = 0
    for i in range(len(input) -1):
        if input[i] < input[i+1]:
            count += 1
    return count

def swap_arr(input):
    for i in range(len(input)-2):
        if input[i] == input[i+1]:
            temp = input[i+1]
            input[i+1] = input[i+2]
            input[i+2] = temp

    return input

def print_arr(input):
    for element in input:
        print(str(element) + " ")


if __name__ == '__main__':
    for i in range(test_num):
        solution()
