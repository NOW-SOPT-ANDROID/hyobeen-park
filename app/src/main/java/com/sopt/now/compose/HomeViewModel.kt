package com.sopt.now.compose

class HomeViewModel {
    private val mockFriendList = listOf<User> (
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "곽의진",
            mbti = "ENFP"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "주효은",
            mbti = "INFP"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "이연진",
            mbti = "ESFJ"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "이가을",
            mbti = "INFP"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "배지현",
            mbti = "ESFJ"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "최준서",
            mbti = "ENTJ"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "김언지",
            mbti = "ISTJ"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "손민재",
            mbti = "ESFP"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "박동민",
            mbti = "ESTP"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "공세영",
            mbti = "INTJ"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "김명석",
            mbti = "ENFP"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "윤서희",
            mbti = "ESFP"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "이나경",
            mbti = "ENTJ"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "박유진",
            mbti = "ISTP"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "우상욱",
            mbti = "INFP"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "송혜음",
            mbti = "ISFJ"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "이유빈",
            mbti = "ENFP"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "김아린",
            mbti = "INTJ"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "배찬우",
            mbti = "INFP"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "김윤서",
            mbti = "ESFJ"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "송혜음",
            mbti = "ISFJ"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "신민석",
            mbti = "ISFP"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "임하늘",
            mbti = "INTP"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "유정현",
            mbti = "ESTJ"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "이석찬",
            mbti = "ISFJ"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "이석준",
            mbti = "ISFP"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "강문수",
            mbti = "ISTJ"
        ),
        User(
            profileImage = R.drawable.ic_launcher_foreground,
            nickname = "이현진",
            mbti = "ENTJ"
        )
    )

    fun getFriendsData(): List<User> = mockFriendList
}