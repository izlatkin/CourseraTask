import random


def print_chess_field(s):
    print('  ABCDEFGH')
    for i in range(0, 8):
        print i, s[i * 8:(i + 1) * 8]


def generate_chess_field():
    chess = random.randint(0, 2**64)
    print chess
    chess = bin(chess)[2:]
    print len(chess)
    print('random field: ')
    print_chess_field(chess)
    return chess


def change_random_coin():
    coin = random.randint(1, 64)
    print('---------------')
    print('revirce coin: ')
    print coin
    print coin % 8
    print coin / 8, chr(ord('A') + (coin % 8))


def main():
    generate_chess_field()
    change_random_coin()

if __name__ == '__main__':
    main()

