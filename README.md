# ShadowMaster



### ColorSlider
- R,G,B 슬라이더가 각각 있고 조합으로 색을 만들 수 있습니다. 

### Elevation Shadow

Material Design의 표준 shadow를 사용했습니다. 
elevation에 따라 그림자를 생성하며, ambient와 spot 두 가지 광원 모델을 조합합니다.

- **Elevation** (0-40dp): 컴포넌트의 Z축 높이를 제어하며, 값이 클수록 그림자가 더 크고 흐릿해짐
- **Corner Radius** (0-100dp): 그림자가 적용되는 박스의 모서리 둥글기
- **Ambient Alpha** (0.0-1.0): 주변광에 의한 그림자의 투명도 = 그림자 강도
- **Spot Alpha** (0.0-1.0): 직접광(위에서 비추는 빛)에 의한 그림자의 투명도를 조정

### Drop Shadow

`Modifier.dropShadow` 커스텀 확장 함수를 사용한 모드입니다. 
Canvas의 `BlurMaskFilter`를 Figma의 Drop Shadow와 유사하게 보이도록 했습니다.

- **Corner Radius** (0-100dp): 그림자가 적용되는 박스의 모서리 둥글기
- **Shadow Alpha** (0.0-1.0): 그림자 전체의 투명도/강도
- **Offset X** (-40dp ~ +40dp): 그림자의 수평 방향 이동 거리. 음수는 왼쪽, 양수는 오른쪽으로 이동
- **Offset Y** (-40dp ~ +40dp): 그림자의 수직 방향 이동 거리. 음수는 위쪽, 양수는 아래쪽으로 이동
- **Blur Radius** (0-100dp): 그림자 테두리의 흐림 정도. 값이 클수록 그림자가 더 부드럽고 넓게 퍼짐