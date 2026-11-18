# Configuration / コンフィグ設定

Configuration file location / 設定ファイルの場所:
`config/apotheosis_bookshelf-common.toml`

---

## English

### `[bookshelves]`

#### `disableCurseOnShelves`
- **Type**: `Boolean`
- **Default**: `true`
- **Description**: When `true`, the Treasure Bookshelf and Anvil Bookshelf prevent curse enchantments (such as Curse of Binding or Curse of Vanishing) from appearing on the Enchanting Table. When `false`, curse enchantments can still be rolled.

---

## 日本語

### `[bookshelves]`

#### `disableCurseOnShelves`
- **データ型**: `Boolean` (`true` / `false`)
- **デフォルト値**: `true`
- **挙動**:
  - `true` の場合、お宝本棚および金床本棚がエンチャントテーブルに組み込まれている間、呪い系エンチャント（束縛の呪い、消滅の呪い等）が付与候補から除外されます。
  - `false` の場合、呪いエンチャントの除外を無効化し、通常通り付与されるようになります。
