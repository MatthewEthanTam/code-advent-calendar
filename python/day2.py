import sys
def read_in_lines(file_name) -> list[str]:
    with open(file_name) as f:
        return [line.strip() for line in f]

def extract_games_and_amounts(thresholds: dict, lines: list[str])-> int:
    result = 0
    for line in lines:
        is_invalid = False
        game_num = int(line.split(':')[0].removeprefix('Game '))
        for sets in line.split(':')[1].split(';'):
            for cubes in sets.split(','):
                if len(cubes.split(' ')) == 3:
                    cubes = cubes[1:]
                amount = int(cubes.split(' ')[0])
                color = cubes.split(' ')[1]
                if amount > thresholds[color]:
                    is_invalid = True
                    break
            if is_invalid:
                break
        
        if not is_invalid:
            result += game_num

    return result

def sum_of_power_of_sets(lines: list[str])-> int:
    result = 0
    for line in lines:
        min_cubes = {"blue": 0, "green": 0, "red": 0}
        for sets in line.split(':')[1].split(';'):
            for cubes in sets.split(','):
                if len(cubes.split(' ')) == 3:
                    cubes = cubes[1:]
                amount = int(cubes.split(' ')[0])
                colour = cubes.split(' ')[1]
                if min_cubes[colour] < amount:
                    min_cubes[colour] = amount
        result += min_cubes["blue"] * min_cubes["green"] * min_cubes["red"]
        
    return result





if __name__ == '__main__':
    
    lines = read_in_lines(sys.argv[1])
    # actualy_cubes = {
    #     "red": 12,
    #     "green": 13,
    #     "blue": 14
    # }
    # print(extract_games_and_amounts(actualy_cubes, lines))
    print(sum_of_power_of_sets(lines))
