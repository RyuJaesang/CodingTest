# Python
from operator import itemgetter
import requests

### TEST
success_receive_data = {
    "statusCode": 200,
    "data": [
        {
            "country": "Japan",
            "countryCode": "JP",
            "newConfirmed": 10,
            "date": "2020-04-05T06:37:00Z"
        },
        {
            "country": "Korea",
            "countryCode": "KR",
            "newConfirmed": 4,
            "date": "2020-04-05T06:37:00Z"
        },
        {
            "country": "China",
            "countryCode": "CN",
            "newConfirmed": 20,
            "date": "2020-04-05T06:37:00Z"
        },
    ]
}
success_send_data = {
    "statusCode": 200
}


fail_data = {
    "statusCode": 500,
    "errorMessage": "서버 에러가 발생하였습니다."
}


def get_top_n(n):
    if n not in [3, 5, 10]:
        return 400  # bad request
    try:
        receive_data = get_new_confirm_data(test=True)
        status_code = receive_data.get("statusCode",-1)
        if status_code > 300 or status_code < 200:
            return status_code

        data_set = receive_data.get("data",[])
        sorted_data_set = sorted(data_set, key=itemgetter('newConfirmed'), reverse=True)

        response_data_set = [{"rank": i + 1, "countryCode": v["countryCode"]} for i, v in enumerate(sorted_data_set)]
        # print({"newConfirmed": response_data_set[:n]})
        send_response = send_sorted_result({"newConfirmed": response_data_set[:n]},test=True)
        return send_response.get("statusCode", -1)

    except Exception as e:
        print(e)
        return -1


def get_new_confirm_data(test=False):
    if test:
        return success_receive_data
    r = requests.get("https://convid19.ncloud.com/api/newConfirmed")
    return r.json()


def send_sorted_result(data, test=False):
    if test:
        return success_send_data
    r = requests.post("https://dashboard.ncloud.com/covid19/api/newConfirmed", data=data)
    return r.json()

### TEST
# print(get_top_n(3))