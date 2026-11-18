# Changelog / 変更履歴

## [1.0.4-beta] - 2026-11-18

### English
- Promoted to Beta release after in-game verification.

### 日本語
- 実機動作確認を経てベータ版（Beta）へ昇格。

## [1.0.3-alpha] - 2026-11-18

### English
- Increased the maximum configurable value of Quanta Bookshelf and Arcana Bookshelf from `50` to `100` (Eterna Bookshelf remains `50`).
- Fixed slider dragging in the bookshelf configuration GUI so the bar follows mouse movement while holding down left-click.

### 日本語
- クアンタ本棚とアルカナ本棚の設定上限を `50` から `100` に引き上げました（エテルナ本棚の上限は `50` のまま）。
- 本棚設定GUIのスライダーについて、左クリックを押しながらマウスを動かしてバーを操作できるよう修正しました。

## [1.0.2-alpha] - 2026-11-18

### English
- Moved `AnvilEnchantingContext` out of the `mixin` package into `dev.apotheosis_bookshelf.util` to fix `IllegalClassLoadError` when Mixin-transformed classes reference it.

### 日本語
- Mixin 適用クラスからの直接参照による `IllegalClassLoadError` を修正するため、`AnvilEnchantingContext` を `dev.apotheosis_bookshelf.util` パッケージへ移動。

## [1.0.1-alpha] - 2026-11-18

### English
- Fixed `ApothEnchantmentMenuMixin` failing to apply in production (SRG-obfuscated) environments by targeting both `getEnchantmentList` and `m_39471_`.

### 日本語
- 本番（SRG難読化）環境で `ApothEnchantmentMenuMixin` の適用に失敗する不具合を修正（`getEnchantmentList` と `m_39471_` の両方をターゲットに追加）。

## [1.0.0-alpha] - 2026-11-18

### English
- Initial alpha release for Minecraft 1.20.1 (Forge).
- Added Eterna, Quanta, and Arcana Bookshelves with right-click GUI to configure stat values (1-50).
- Added Treasure Bookshelf and Anvil Bookshelf with configurable curse prevention.
- Added Primal End Crystal (right-click Ender Dragon with End Crystal) and Primal Nether Star (right-click Wither with Nether Star).
- Added crafting recipes including the 3-way bookshelf conversion loop and Library of Alexandria recipe.
- Added bilingual (English / Japanese) localization and documentation.

### 日本語
- Minecraft 1.20.1 (Forge) 向け初期アルファ版をリリース。
- 右クリックGUIで数値を1〜50に設定できるエテルナ本棚（上限50）、クアンタ本棚、アルカナ本棚を追加。
- コンフィグで呪い無効を切り替え可能なお宝本棚、金床本棚を追加。
- 原初のエンドクリスタル（エンドクリスタルをエンダードラゴンに右クリック）、原初のネザースター（ネザースターをウィザーに右クリック）を追加。
- 3種本棚のループ変換レシピやアレクサンドリア書庫のレシピを含む各種クラフトレシピを追加。
- 日英バイリンガル対応（ゲーム内テキストおよび各種ドキュメント）を実装。
