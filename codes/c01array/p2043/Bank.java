package codes.c01array.p2043;
// https://books.halfrost.com/leetcode/ChapterFour/2000~2099/2043.Simple-Bank-System/
// https://leetcode.com/problems/simple-bank-system/

/*
你的任务是为一个很受欢迎的银行设计一款程序，以自动化执行所有传入的交易（转账，存款和取款）。
银行共有 n 个账户，编号从 1 到 n 。每个账号的初始余额存储在一个下标从 0 开始的整数数组 balance 中，
其中第 (i + 1) 个账户的初始余额是 balance[i] 。

如果满足下面全部条件，则交易 有效 ：
* 指定的账户数量在 1 和 n 之间，且
* 取款或者转账需要的钱的总数 小于或者等于 账户余额。
实现 Bank 类：
Bank(long[] balance) 
    使用下标从 0 开始的整数数组 balance 初始化该对象。
boolean transfer(int account1, int account2, long money) 
    从编号为 account1 的账户向编号为 account2 的账户转帐 money 美元。如果交易成功，返回 true ，否则，返回 false 。
boolean deposit(int account, long money) 
    向编号为 account 的账户存款 money 美元。如果交易成功，返回 true ；否则，返回 false 。
boolean withdraw(int account, long money) 
    从编号为 account 的账户取款 money 美元。如果交易成功，返回 true ；否则，返回 false 。

n == balance.length
1 <= n, account, account1, account2 <= 10^5
0 <= balance[i], money <= 10^12
transfer, deposit, withdraw 三个函数，每个 最多调用 10^4 次
*/

class Bank {
    private long[] balance;
    public Bank(long[] balance){
        this.balance = balance;
    }
    public boolean transfer(int account1, int account2, long money){
        if (account1 > balance.length || account2 > balance.length || money > balance[account1-1])
            return false;
        balance[account1-1] -= money;
        balance[account2-1] += money;
        return true;
    }
    public boolean deposit(int account, long money) {
        if (account > balance.length)
            return false;
        balance[account-1] += money;
        return true;
    }
    public boolean withdraw(int account, long money) {
        if (account > balance.length || money > balance[account-1])
            return false;
        balance[account-1] -= money;
        return true;
    }
    public static void main(String[] args) {
        long[] ct = new long[]{10,100,20,50,30};
        Bank bank = new Bank(ct);
        bank.withdraw(3, 10);
        bank.transfer(5, 1, 20);
        bank.deposit(5, 20);
        bank.transfer(3, 4, 15);
        bank.withdraw(10, 50);
    }
}
/*
10, 100, 20, 50, 30
10, 100, 10, 50, 30     true
30, 100, 10, 50, 10     true
30, 100, 10, 50, 30     true
*/