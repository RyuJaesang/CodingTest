nq = input()
n = int(nq.split()[0])
q = int(nq.split()[1])

dictionary = input()
dictionary = list(dictionary)

q_list = []

def solution():
    for i in range(q):
        q_temp = input()
        left = int(q_temp.split()[0])
        right = int(q_temp.split()[1])
        q_list.append([left, right])

    print_arr(q_list)

    dp_arr = create_dp(dictionary)
    print(dp_arr)

def calculate_result(q, dp_arr):
    dp_arr[q[0]]

def create_dp(dictionary):
    dp_arr = []
    for i in range(len(dictionary)):
        if i == 0:
            dp_arr.append([dictionary[0], 1])

        else:
            temp = []
            temp.append(dp_arr[i-1])
            print(temp)

            if temp[-1][0] == dictionary[i]:
                temp[-1][1]+=1
            else :
                temp.append([dictionary[i], 1])
            dp_arr.append(temp)


    return dp_arr

def print_arr(input):
    for element in input:
        print(str(element) + " ")


if __name__ == '__main__':
    solution()
