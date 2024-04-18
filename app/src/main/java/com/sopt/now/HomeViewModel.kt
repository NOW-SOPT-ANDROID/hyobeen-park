package com.sopt.now

import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    private val mockFriendList = listOf<Friend> (
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "곽의진",
            mbti = "ENFP"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "주효은",
            mbti = "INFP"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "이연진",
            mbti = "ESFJ"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "이가을",
            mbti = "INFP"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "배지현",
            mbti = "ESFJ"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "최준서",
            mbti = "ENTJ"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "김언지",
            mbti = "ISTJ"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "손민재",
            mbti = "ESFP"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "박동민",
            mbti = "ESTP"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "공세영",
            mbti = "INTJ"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "김명석",
            mbti = "ENFP"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "윤서희",
            mbti = "ESFP"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "이나경",
            mbti = "ENTJ"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "박유진",
            mbti = "ISTP"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "우상욱",
            mbti = "INFP"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "송혜음",
            mbti = "ISFJ"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "이유빈",
            mbti = "ENFP"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "김아린",
            mbti = "INTJ"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "배찬우",
            mbti = "INFP"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "김윤서",
            mbti = "ESFJ"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "송혜음",
            mbti = "ISFJ"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "신민석",
            mbti = "ISFP"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "임하늘",
            mbti = "INTP"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "유정현",
            mbti = "ESTJ"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "이석찬",
            mbti = "ISFJ"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "이석준",
            mbti = "ISFP"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "강문수",
            mbti = "ISTJ"
        ),
        Friend(
            profileImage = R.drawable.ic_person_white_24,
            nickname = "이현진",
            mbti = "ENTJ"
        )
    )

    fun getFriendInfo() : List<Friend> {
        return mockFriendList
    }
}