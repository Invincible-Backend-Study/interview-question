/**
 * Unit에서 제공하는 기본 타입입니다.
 */
export type UnitType = 'SEARCH'
  | 'ADD'
  | 'DELETE'
  | 'CANCEL'
  | 'SAVE'

type UnitAction = () => void;

interface Unit {
  /**
   * Unit을 클릭했을 때 동작하는 이벤트입니다.
   */
  action: UnitAction

  /**
   * unit의 가시성 여부입니다.
   */
  visible: boolean

  /**
   * unit 사용 여부입니다.
   */
  disabled: boolean
}

export interface EditorProps {
  units: Record<UnitType, Unit>
}

